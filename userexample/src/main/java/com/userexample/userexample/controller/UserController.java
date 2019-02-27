package com.userexample.userexample.controller;

import com.userexample.userexample.Recommend;
import com.userexample.userexample.UserexampleApplication;
import com.userexample.userexample.bean.*;
import com.userexample.userexample.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRecordService userRecordService;
    @Autowired
    private TopPicService topPicService;
    @Autowired
    private FeedbackService feedbackService;

    private String loginName = "";
    private int searchID = 0;
    private String str = "";
    private String preStr = "";

    private int tagID = 0;
    private int totalID = 0;
    private boolean recommendState = true;
    private int entertainmentID = 0;
    private int sportsID = 0;
    private int financeID = 0;
    private int chinanewsID = 0;
    private int worldnewsID = 0;
    private int societyID = 0;
    private int educationID = 0;
    private List<News> news = new ArrayList();
    private int[] result = new int[10];

    @GetMapping
    public String index(Model model){

        int i;
        model.addAttribute("loginName", loginName);

        if(!loginName.equals("")){
            List<UserRecord> userRecord = userRecordService.findByName(loginName);
            model.addAttribute("user_record",userRecord);
            model.addAttribute("browseCol",userRecordService.getColumn(loginName));
            List<Comment> comment = commentService.findByName(loginName);
            model.addAttribute("commentLog",comment);
            model.addAttribute("commentLogCol",commentService.getCol(loginName));
        }


        List<TopPic> topPic = topPicService.getAll();
        model.addAttribute("img1",topPic.get(0).getPic());
        model.addAttribute("pretitle1",topPic.get(0).getTitle());
        model.addAttribute("text1",topPic.get(0).getText());
        model.addAttribute("img2",topPic.get(1).getPic());
        model.addAttribute("pretitle2",topPic.get(1).getTitle());
        model.addAttribute("text2",topPic.get(1).getText());
        model.addAttribute("img3",topPic.get(2).getPic());
        model.addAttribute("pretitle3",topPic.get(2).getTitle());
        model.addAttribute("text3",topPic.get(2).getText());
        model.addAttribute("img4",topPic.get(3).getPic());
        model.addAttribute("pretitle4",topPic.get(3).getTitle());
        model.addAttribute("text4",topPic.get(3).getText());
        model.addAttribute("img5",topPic.get(4).getPic());
        model.addAttribute("pretitle5",topPic.get(4).getTitle());
        model.addAttribute("text5",topPic.get(4).getText());
        model.addAttribute("img6",topPic.get(5).getPic());
        model.addAttribute("pretitle6",topPic.get(5).getTitle());
        model.addAttribute("text6",topPic.get(5).getText());

        news.clear();

        if(tagID == 1){
            news = newsService.pageByTag(1, entertainmentID, 10);
        }
        else if(tagID == 2){
            news = newsService.pageByTag(2,sportsID,10);
        }
        else if(tagID == 3){
            news = newsService.pageByTag(3, financeID,10);
        }
        else if(tagID == 4){
            news = newsService.pageByTag(4,chinanewsID,10);
        }
        else if(tagID == 5){
            news = newsService.pageByTag(5,worldnewsID,10);
        }
        else if(tagID == 6){
            news = newsService.pageByTag(6,societyID,10);
        }
        else if(tagID == 7){
            news = newsService.pageByTag(7,educationID,10);
        }
        else{

            if(!loginName.equals("") && recommendState){
                List<Integer> userRecordList = userRecordService.getRecord(loginName);
                //System.out.println(userRecordList.toString());
                result = null;
                result = UserexampleApplication.recommend.recommnend(userRecordList);

                /*for(i=0;i<result.length;i++){
                    System.out.print(result[i] + " ");
                    System.out.println(newsService.getRecordNews(result[i]).getTitle());
                }*/

                for(i=0;i<result.length;i++){
                    news.add(newsService.getRecordNews(result[i]));
                    //System.out.println(result[i]);
                    //System.out.println(news.get(i).getTitle());
                }
            }
            else if(!loginName.equals("")){
                for(i=0;i<result.length;i++){
                    news.add(newsService.getRecordNews(result[i]));
                }
            }
            else{
                news = newsService.page(totalID, 10);
            }

        }
        model.addAttribute("tagNum", tagID);

        for(i=0; i<news.size(); i++){
            model.addAttribute("title"+(i+1),news.get(i).getTitle());
            model.addAttribute("img_address"+(i+1),news.get(i).getImg_address());
            model.addAttribute("heat"+(i+1),news.get(i).getHeat());
            model.addAttribute("time"+(i+1),news.get(i).getTime());
            model.addAttribute("news_address"+(i+1),news.get(i).getNews_address());
            model.addAttribute("summary"+(i+1),news.get(i).getSummary());
            model.addAttribute("number"+(i+1),news.get(i).getNumber());
        }
        model.addAttribute("colPreNum",i);

        List<Comment> comment;
        for(i=0; i<news.size(); i++){
            comment = commentService.findByNews(news.get(i).getTitle());
            model.addAttribute("comment"+(i+1),comment);
        }

        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "name",defaultValue = "") String name,
                        @RequestParam(value = "password",defaultValue = "") String password, Model model){
        Optional<User> user = userService.login(name, password);
        if(!user.isPresent()){
            return "redirect:/";
        }
        loginName = name;

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        loginName = "";
        return "redirect:/";
    }

    @GetMapping("/regist")
    public String regist(){
        return "regist";
    }

    @PostMapping("/regist")
    public String register(@RequestParam(value = "name",defaultValue = "") String name,
                           @RequestParam(value = "phone",defaultValue = "") String phone, Model model){
        List<User> user = userService.findByName(name);
        if(user.size() != 0){
            model.addAttribute("login","用户名重复");
            return "regist";
        }
        user = userService.findByPhone(phone);
        if(user.size()!=0){
            model.addAttribute("login","电话号码重复");
            return "regist";
        }

        try {
            Thread.sleep(3000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return "redirect:/";
    }

    @PostMapping("/list")
    public String list(@RequestParam("list") String title){
        List<News> news = newsService.findByTitle(title);
        List<UserRecord> userRecord;
        if(!loginName.equals("") && news.size()!=0){
            userRecord = userRecordService.findByID(loginName, news.get(0).getTitle());
            if(userRecord.size()==0){
                newsService.addHeat(news.get(0).getNewsID());
                SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String datetime = tempDate.format(new Date());
                userRecordService.addUserRecord(loginName, news.get(0).getNewsID(), news.get(0).getTitle(), datetime);
            }
        }
        return "index";
    }

    @PostMapping("/comment")
    public String comment(@RequestParam("list") String title, @RequestParam("textarea") String text){
        List<News> news = newsService.findByTitle(title);

        if(text.equals("")){
            return "redirect:/";
        }

        if(!loginName.equals("") && news.size()!=0){
            Optional<Comment> comment = commentService.findComment(loginName, news.get(0).getTitle());
            if(comment.isPresent()){
                commentService.updateComment(loginName, news.get(0).getTitle(), comment.get().getNumber(), text);
            }
            else{
                commentService.addComment(loginName, news.get(0).getTitle(), 0, text);
            }
        }

        return "redirect:/";
    }

    @PostMapping("/changeUser")
    public String changeUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("phone") String phone){
        List<User> user = userService.findByName(name);
        userService.updateUser(user.get(0).getUserID(), name, phone, password);

        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@RequestParam("text") String text, Model model){
        if(text.equals("")){
            return "redirect:/";
        }

        preStr = text;
        str = "%";
        str += text;
        str += '%';

        return "redirect:/search";
    }

    @GetMapping("/search")
    public String searchGet(Model model){
        int i;

        searchID = 0;
        List<News> news = newsService.search(str, str);
        model.addAttribute("searchContent",preStr);

        if(news.size()%10 == 0){
            model.addAttribute("searchCol",news.size()/10);
        }
        else{
            model.addAttribute("searchCol",news.size()/10 + 1);
        }

        model.addAttribute("colNum",news.size());
        model.addAttribute("pageNum",searchID/10 + 1);

        for(i = 0; i<news.size() && i<10; i++){
            model.addAttribute("title"+(i+1),news.get(i).getTitle());
            model.addAttribute("img_address"+(i+1),news.get(i).getImg_address());
            model.addAttribute("heat"+(i+1),news.get(i).getHeat());
            model.addAttribute("time"+(i+1),news.get(i).getTime());
            model.addAttribute("news_address"+(i+1),news.get(i).getNews_address());
            model.addAttribute("summary"+(i+1),news.get(i).getSummary());
            model.addAttribute("number"+(i+1),news.get(i).getNumber());
        }
        model.addAttribute("colPreNum",i);

        List<Comment> comment;
        for(i=0; i<news.size() && i<10; i++){
            comment = commentService.findByNews(news.get(i).getTitle());
            model.addAttribute("comment"+(i+1),comment);
        }

        return "search";
    }

    @PostMapping("/search/pagePrevious")
    public String pagePrevious(@RequestParam("text") String text, Model model){
        int i, j;

        searchID = Integer.parseInt(text);

        List<News> news = newsService.search(str, str);

        model.addAttribute("colNum",news.size());

        if(news.size()%10 == 0){
            model.addAttribute("searchCol",news.size()/10);
        }
        else{
            model.addAttribute("searchCol",news.size()/10 + 1);
        }

        model.addAttribute("pageNum",searchID);

        searchID--;
        searchID*=10;

        for(i = searchID, j=0 ; i<news.size() && i<searchID+10; i++, j++){
            model.addAttribute("title"+(j+1),news.get(i).getTitle());
            model.addAttribute("img_address"+(j+1),news.get(i).getImg_address());
            model.addAttribute("heat"+(j+1),news.get(i).getHeat());
            model.addAttribute("time"+(j+1),news.get(i).getTime());
            model.addAttribute("news_address"+(j+1),news.get(i).getNews_address());
            model.addAttribute("summary"+(j+1),news.get(i).getSummary());
            model.addAttribute("number"+(j+1),news.get(i).getNumber());
        }
        model.addAttribute("colPreNum",j);

        List<Comment> comment;
        for(i=searchID, j=0; i<news.size() && i<searchID+10; i++, j++){
            comment = commentService.findByNews(news.get(i).getTitle());
            model.addAttribute("comment"+(j+1),comment);
        }

        return "search";
    }

    @PostMapping("/search/pageNext")
    public String pageNext(@RequestParam("text") String text, Model model){
        int i, j;

        searchID = Integer.parseInt(text);

        List<News> news = newsService.search(str, str);

        model.addAttribute("colNum",news.size());

        if(news.size()%10 == 0){
            model.addAttribute("searchCol",news.size()/10);
        }
        else{
            model.addAttribute("searchCol",news.size()/10 + 1);
        }

        model.addAttribute("pageNum",searchID);

        searchID--;
        searchID*=10;

        for(i = searchID, j=0 ; i<news.size() && i<searchID+10; i++, j++){
            model.addAttribute("title"+(j+1),news.get(i).getTitle());
            model.addAttribute("img_address"+(j+1),news.get(i).getImg_address());
            model.addAttribute("heat"+(j+1),news.get(i).getHeat());
            model.addAttribute("time"+(j+1),news.get(i).getTime());
            model.addAttribute("news_address"+(j+1),news.get(i).getNews_address());
            model.addAttribute("summary"+(j+1),news.get(i).getSummary());
            model.addAttribute("number"+(j+1),news.get(i).getNumber());
        }
        model.addAttribute("colPreNum",j);

        List<Comment> comment;
        for(i=searchID, j=0; i<news.size() && i<searchID+10; i++, j++){
            comment = commentService.findByNews(news.get(i).getTitle());
            model.addAttribute("comment"+(j+1),comment);
        }

        return "search";
    }

    @GetMapping("/recommend")
    public String recommend(Model model){
        if(tagID != 0){
            tagID = 0;
            recommendState = false;
        }
        else if(loginName.equals("")){
            totalID+=10;
        }
        else{
            recommendState = true;
        }
        return "redirect:/";
    }

    @GetMapping("/entertainment")
    public String entertainment(Model model){
        if(tagID != 1){
            tagID = 1;
        }
        else{
            entertainmentID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/sports")
    public String sports(Model model){
        if(tagID != 2){
            tagID = 2;
        }
        else{
            sportsID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/finance")
    public String finance(Model model){
        if(tagID != 3){
            tagID = 3;
        }
        else{
            financeID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/chinanews")
    public String chinanews(Model model){
        if(tagID != 4){
            tagID = 4;
        }
        else{
            chinanewsID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/worldnews")
    public String worldnews(Model model){
        if(tagID != 5){
            tagID = 5;
        }
        else{
            worldnewsID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/society")
    public String society(Model model){
        if(tagID != 6){
            tagID = 6;
        }
        else{
            societyID+=10;
        }
        return "redirect:/";
    }

    @GetMapping("/education")
    public String education(Model model){
        if(tagID != 7){
            tagID = 7;
        }
        else{
            educationID+=10;
        }
        return "redirect:/";
    }

    @PostMapping("/submitLike")
    public String submitLike(@RequestParam("name") String name, @RequestParam("comment") String comment){
        Optional<Comment> userComment = commentService.findComment(name, comment);

        if(userComment.isPresent()){
            commentService.addNumber(userComment.get().getName(), userComment.get().getNews());
        }

        return "redirect:/";
    }

    @PostMapping("/feedback")
    public String feedback(@RequestParam(value = "email",defaultValue = "") String email, @RequestParam(value = "comment",defaultValue = "") String comment){
        if(email.equals("") || comment.equals("")){
            return "redirect:/";
        }
        feedbackService.addFeedback(email, comment);
        return "redirect:/";
    }
}
