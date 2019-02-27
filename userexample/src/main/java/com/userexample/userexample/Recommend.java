package com.userexample.userexample;

import com.userexample.userexample.bean.Word;
import com.userexample.userexample.repository.WordRepository;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class Recommend {
    @Autowired
    private WordRepository wordRepository;

    private List<Integer> docList;                              //文档编号列表
    private Integer docNum;                                     //文档总数
    private Map<Integer,Integer> docToIndex=new HashMap<>();    //建立文档到索引的映射
    private List<String> wordList;                              //单词列表
    private Integer wordNum;                                    //单词总数
    private Map<String,Integer> wordToIndex=new HashMap<>();    //建立单词到索引的映射
    private double[][] weight;                                  //文档向量矩阵

    private static Recommend recommend;

    public Recommend(){}

    @PostConstruct
    public void init(){
        recommend=this;
        recommend.wordRepository=this.wordRepository;
    }

    //文档向量化
    public void calculateVectorMatrix(){
        this.docList=recommend.wordRepository.finAllDoc();
        this.docNum=docList.size();
        this.wordList=recommend.wordRepository.findAllWord();
        this.wordNum=wordList.size();
        for(int i=0;i<docNum;i++)
            this.docToIndex.put(docList.get(i),i);
        for(int i=0;i<wordNum;i++)
            this.wordToIndex.put(wordList.get(i),i);
        this.weight=new double[docNum][wordNum];

        int[] wordSumOfDoc=new int[docNum];                     //某文档的分词总数
        int[] docSumOfWord=new int[wordNum];                    //包括某分词的文档总数
        List<Word> allWords=recommend.wordRepository.findAll(); //遍历数据库所有记录
        for(Word word:allWords){
            int docIndex=docToIndex.get(word.getDoc());
            int wordIndex=wordToIndex.get(word.getWord());
            int num=word.getNum();
            wordSumOfDoc[docIndex]+=num;
            docSumOfWord[wordIndex]++;
            weight[docIndex][wordIndex]=num;
        }

        //计算TF-IDF
        for(int d=0;d<docNum;d++){
            for(int w=0;w<wordNum;w++){
                double tf=(1.0)*weight[d][w]/wordSumOfDoc[d];
                double idf=Math.log10((1.0)*docNum/docSumOfWord[w]);
                weight[d][w]=tf*idf;
            }
        }
    }

    //特征选择
    private void reductionMatrixDimension(){}

    //计算余弦相似度
    private double calculateCosineSimilarity(final double[] x,final double[] y){
        double m=0.0;
        double d1=0.0;
        double d2=0.0;
        for(int i=0;i<wordNum;i++){
            m=m+x[i]*y[i];
            d1=d1+x[i]*x[i];
            d2=d2+y[i]*y[i];
        }
        return m/(Math.sqrt(d1)*Math.sqrt(d2));
    }

    //分簇
    private double clustering(final double[][] mean,List<Integer>[] cluster){
        double cosineSum=0.0;
        for(int i=0;i<docNum;i++){
            double cosine=calculateCosineSimilarity(weight[i],mean[0]);
            double tmp;
            int label=0;
            for(int j=0;j<mean.length;j++){
                tmp=calculateCosineSimilarity(weight[i],mean[j]);
                if(tmp>cosine){
                    cosine=tmp;
                    label=j;
                }
            }
            cluster[label].add(docList.get(i));
            cosineSum+=cosine;
        }
        return cosineSum;
    }

    //计算每个簇质心
    private double[][] calculateMean(final List<Integer>[] cluster){
        double[][] mean=new double[cluster.length][wordNum];
        for(int i=0;i<cluster.length;i++){
            for(int j=0;j<cluster[i].size();j++){
                for(int k=0;k<wordNum;k++){
                    mean[i][k]+=weight[docToIndex.get(cluster[i].get(j))][k];
                }
            }
            int num=cluster[i].size();
            for(int n=0;n<wordNum;n++){
                mean[i][n]=mean[i][n]/num;
            }
        }
        return mean;
    }

    //运行K-means算法
    public List<Integer>[] runKmeans(final int[] user){
        calculateVectorMatrix();
        reductionMatrixDimension();

        double[][] mean=new double[user.length][wordNum];     //k个质心
        for(int i=0;i<user.length;i++){
            mean[i]=weight[docToIndex.get(user[i])];
        }
        List<Integer>[] cluster;                                //k个簇

        double newCosSum=0.0;
        double oldCosSum;
        do{
            cluster=new ArrayList[mean.length];
            for(int i=0;i<cluster.length;i++){
                cluster[i]=new ArrayList<>();
            }
            oldCosSum=newCosSum;
            newCosSum=clustering(mean,cluster);
            mean=calculateMean(cluster);
        }while(Math.abs(newCosSum-oldCosSum)>1);
        return cluster;
    }

    //权重归一化
    private double[][] weightNormalization(){
        double[][] weightN=new double[docNum][wordNum];
        double[] tmp=new double[docNum];
        for(int d=0;d<docNum;d++){
            for(int w=0;w<wordNum;w++){
                tmp[d]+=weight[d][w]*weight[d][w];
            }
        }
        for(int d=0;d<docNum;d++){
            for(int w=0;w<wordNum;w++){
                weightN[d][w]=weight[d][w]/Math.sqrt(tmp[d]);
            }
        }
        return weightN;
    }

    //计算用户特征
    private double[] calculateUserCharacteristic(final List<Integer> userRecord){
        double[] userCharacteristic=new double[wordNum];
        for(int i=0;i<userRecord.size();i++){
            int index=docToIndex.get(userRecord.get(i));
            for(int j=0;j<wordNum;j++){
                userCharacteristic[j]+=weight[index][j];
            }
        }
        int length=userRecord.size();
        for(int i=0;i<wordNum;i++){
            userCharacteristic[i]=userCharacteristic[i]/length;
        }
        return userCharacteristic;
    }

    //生成推荐列表
    public int[] recommnend(final List<Integer> userRecord){
        double[] userCharacteristic=calculateUserCharacteristic(userRecord);

        double[] result=new double[docNum];
        for(int i=0;i<docNum-userRecord.size();i++){
            result[i]=calculateCosineSimilarity(weight[i],userCharacteristic);
        }
        for(int record:userRecord){
            result[docToIndex.get(record)]=-1.0;
        }

        List<Pair<Integer,Double>> tmp=new ArrayList<>();
        for(int i=0;i<docNum;i++){
            tmp.add(new Pair(i,result[i]));
        }
        Collections.sort(tmp, new Comparator<Pair<Integer, Double>>() {
            @Override
            public int compare(Pair<Integer, Double> o1, Pair<Integer, Double> o2) {
                if (o1.getValue() < o2.getValue())
                    return 1;
                else{
                    if(o1.getValue()==o2.getValue())
                        return 0;
                    else
                        return -1;
                }
            }
        });

        int[] recommend;
        if(tmp.size()>10){
            recommend=new int[10];
            for(int i=0;i<recommend.length;i++){
                recommend[i]=docList.get(tmp.get(i).getKey());
            }
        }
        else{
            recommend=new int[tmp.size()];
            for(int i=0;i<recommend.length;i++){
                recommend[i]=docList.get(tmp.get(i).getKey());
            }
        }

        return recommend;
    }
}
