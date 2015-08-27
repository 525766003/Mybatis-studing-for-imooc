package com.imooc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.bean.Message;

/*
 * 根据查询条件查询数据列表
 */
public class MessageDao {
	public List<Message> queryMessageList(String command,String description) {
		List<Message> list = new ArrayList<Message>();
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mico_message","root","123456");
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from MESSAGE where 1=1");
			List<String> pList = new ArrayList<String>();
			if(command != null && !"".equals(command)){
				sql.append(" and COMMAND=?");
				pList.add(command);
			}
			if(description != null && !"".equals(description)){
				sql.append(" and DESCRIPTION like '%' ? '%'");
				pList.add(description);
			}
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			for(int i=0;i<pList.size();i++){
				statement.setString(i+1, pList.get(i));
			}
			ResultSet rSet = statement.executeQuery();
			
			while(rSet.next()){
				Message message = new Message();
				message.setId(rSet.getString("ID"));
				message.setCommand(rSet.getString("COMMAND"));
				message.setDescription(rSet.getString("DESCRIPTION"));
				message.setContent(rSet.getString("CONTENT"));
				list.add(message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
