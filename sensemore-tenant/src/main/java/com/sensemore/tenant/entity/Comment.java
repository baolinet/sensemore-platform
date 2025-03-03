package com.sensemore.tenant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "comment")
public class Comment {

    /**
     * MongoDB文档的唯一标识符，作为主键使用。
     */
    @Id
    private String id;

    /**
     * 文章的唯一标识符，用于关联评论和文章。
     */
    @Field("article_id")
    private String articleId;

    /**
     * 用户发表的评论文本内容。
     */
    private String content;

    /**
     * 发表评论的用户唯一标识符。
     */
    @Field("user_id")
    private String userId;

    /**
     * 发表评论的用户昵称，用于显示在评论列表中。
     */
    private String nickname;

    /**
     * 评论创建的时间，格式通常为ISO日期时间格式。
     */
    @Field("create_time")
    private Date createTime;

    /**
     * 评论获得的点赞数量，反映评论的受欢迎程度。
     */
    @Field("like_number")
    private Integer likeNumber;

    /**
     * 评论下方的回复数量，反映评论的互动程度。
     */
    @Field("reply_number")
    private Integer replyNumber;

    /**
     * 评论的可见状态，'0’表示评论不可见，'1’表示评论可见。
     */
    private String state;

    /**
     * 评论的上级评论ID，如果为’0’或空，则表示该评论是顶级评论，没有上级评论。
     */
    @Field("parent_id")
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(Integer replyNumber) {
        this.replyNumber = replyNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", likeNumber=" + likeNumber +
                ", replyNumber=" + replyNumber +
                ", state='" + state + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
    
}