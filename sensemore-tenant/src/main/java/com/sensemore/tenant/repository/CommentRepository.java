package com.sensemore.tenant.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.sensemore.tenant.entity.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CommentRepository {

	@Autowired
    private MongoTemplate mongoTemplate;

    public void showDatabase(){
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println("db.getName() = " + db.getName());
        mongoTemplate.getCollectionNames().forEach(System.out::println);
    }
    
    public void testInsert() {
			Comment comment = new Comment();
		
			comment.setArticleId("article123");
			comment.setContent("这是一个很好的文章！");
			comment.setUserId("1003");
			comment.setNickname("评论者A");
			comment.setCreateTime(new Date());
			comment.setLikeNumber(10);
			comment.setReplyNumber(2);
			comment.setState("1");
			comment.setParentId("0");
		
			mongoTemplate.insert(comment);
			// 拿到插入后的评论ID
			System.out.println("tempComment.getId() = " + comment.getId());
		}

		public void testInsertAll() {
			List<Comment> commentList = new ArrayList<>();

			for (int i = 0; i < 5; i++) {
				Comment comment = new Comment();
				comment.setArticleId("article" + (i + 1));
				comment.setContent("这是第 " + (i + 1) + " 条评论内容");
				comment.setUserId(String.valueOf(1003));
				comment.setNickname("评论者" + (i + 1));
				comment.setCreateTime(new Date());
				comment.setLikeNumber(5 + i); // 假设点赞数从5开始递增
				comment.setReplyNumber(0); // 假设初始回复数为0
				comment.setState("1"); // 假设所有评论都是可见的
				comment.setParentId("0"); // 假设所有评论都是顶级评论

				commentList.add(comment);
			}

			// 批量插入评论
			mongoTemplate.insertAll(commentList);
		}

		public void testFindById() {
			Comment comment = mongoTemplate.findById("67c56ea6523c586f1e294d28", Comment.class);
			System.out.println("comment = " + comment);
		}

		public void testFindByUserIdAndCreateTime() {
			String userId = "1003";
			Query query = new Query();
			Criteria criteria = Criteria.where("user_id").is(userId).and("create_time").lte(new Date());
			query.addCriteria(criteria)
					.skip(0)
					.limit(5);
			List<Comment> commentList = mongoTemplate.find(query, Comment.class);
			commentList.forEach(System.out::println);
		}

    public void testFindByRegex() {
        String keyword = "开水";

        Query query = new Query();
        Criteria criteria = Criteria.where("content").regex(keyword);
        query.addCriteria(criteria);

        List<Comment> commentList = mongoTemplate.find(query, Comment.class);
        commentList.forEach(System.out::println);
    }

    public void testFindALl() {
        List<Comment> commentList = mongoTemplate.findAll(Comment.class);
        commentList.forEach(System.out::println);
    }

    public void testFindByArticleIdWithCreatedTimeAsc() {
        String articleId = "100001";
    
        Criteria criteria = Criteria.where("article_id").is(articleId)
                .and("create_time").lte(new Date());
    
        Query query = new Query();
        query.addCriteria(criteria)
                .with(Sort.by(Sort.Order.asc("create_time")));
    
        List<Comment> commentList = mongoTemplate.find(query, Comment.class);
        commentList.forEach(System.out::println);
    }

    public void testDeleteById() {
        Criteria criteria = Criteria.where("_id").is("2");
        DeleteResult deleteResult = mongoTemplate.remove(new Query(criteria), Comment.class);
        System.out.println("deleteResult.getDeletedCount() = " + deleteResult.getDeletedCount());
    }

    public void testDeleteByUserIdAndCreateTime() {
        String userId = "1003";
        Date date = new Date();
        // 删除两分钟以内发布的评论
        date.setTime(date.getTime() - 2 * 60 * 1000);
    
        Query query = new Query();
        Criteria criteria = Criteria.where("user_id").is(userId)
                .and("create_time").gte(date).lte(new Date())
                .and("state").is("1");
        query.addCriteria(criteria);
    
        DeleteResult deleteResult = mongoTemplate.remove(query, Comment.class);
        System.out.println("deleteResult.getDeletedCount() = " + deleteResult.getDeletedCount());
    }

    public void testUpdateFirst() {
        Comment comment = new Comment();
        comment.setId("6728a523d9496fae23c4c2a9");
        comment.setLikeNumber(102);
    
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(comment.getId()));
    
        Update update = new Update();
        update.set("like_number", comment.getLikeNumber());
    
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Comment.class);
        System.out.println("updateResult.getMatchedCount() = " + updateResult.getMatchedCount());
        System.out.println("updateResult.getModifiedCount() = " + updateResult.getModifiedCount());
    }

    public void testUpdateMulti() {
        Query query = new Query();
        Criteria criteria = Criteria.where("article_id").is("100001").and("state").is("1");
        query.addCriteria(criteria);
    
        Update update = new Update();
        update.set("nickname", "聂可以");
    
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Comment.class);
        System.out.println("updateResult.getMatchedCount() = " + updateResult.getMatchedCount());
        System.out.println("updateResult.getModifiedCount() = " + updateResult.getModifiedCount());
    }

}