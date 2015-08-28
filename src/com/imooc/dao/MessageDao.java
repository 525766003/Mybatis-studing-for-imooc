package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;

/*
 * 
 */
public class MessageDao {
	/*
	 * 根据查询条件查询数据列表
	 */
	public List<Message> queryMessageList(String command,String description){
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
		sqlSession = dbAccess.getSqlSession();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		messageList = sqlSession.selectList("Message.queryMessageList",message);
		//通过Sqlsession执行sql语句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return messageList;
	}
	/*
	 * 单挑删除
	 */
	public void deleteOne(int id){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
		sqlSession = dbAccess.getSqlSession();
		sqlSession.delete("Message.deleteOne",id);
		sqlSession.commit();
		//通过Sqlsession执行sql语句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
	/*
	 * 多条删除
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			sqlSession.delete("Message.deleteBatch",ids);
			sqlSession.commit();
			//通过Sqlsession执行sql语句
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
}
