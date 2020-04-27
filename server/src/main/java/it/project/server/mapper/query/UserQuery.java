package it.project.server.mapper.query;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import it.project.server.pojo.Skill;
import it.project.server.model.UserSearch;
import it.project.server.pojo.Address;
import it.project.server.pojo.Privilege;
import it.project.server.pojo.User;

@Mapper
public interface UserQuery {

	@Results({ @Result(column = "level", property = "privilege.level"),
			@Result(column = "description", property = "privilege.description") })
	@Select("SELECT users.id, users.first_name as firstName, users.last_name as lastName "
			+ ", users.username, users.password, users.dob, users.sex, users.telephone "
			+ ", users.privilege_id, user_privileges.level, user_privileges.description FROM users "
			+ "INNER JOIN user_privileges on user_privileges.id = users.privilege_id "
			+ "WHERE username = #{username} and password = #{password}")
	User checkUser(User user);

	@Select("SELECT users.id, users.first_name as firstName, users.last_name as lastName "
			+ ", users.username, users.dob, users.sex, users.telephone, TIMESTAMPDIFF(YEAR, FROM_UNIXTIME(dob/1000,'%Y-%m-%d'), CURDATE()) eta FROM users WHERE privilege_id = (select id from user_privileges where description = 'USER')")
	List<User> getBasicUsers(RowBounds rowBounds);

	@Results({ @Result(column = "level", property = "privilege.level"),
			@Result(column = "privilege", property = "privilege.description") })
	@Select("SELECT users.id, users.first_name as firstName, users.last_name as lastName"
			+ ", users.username, users.dob, users.sex, users.telephone, users.privilege_id"
			+ ", TIMESTAMPDIFF(YEAR, FROM_UNIXTIME(dob/1000,'%Y-%m-%d'), CURDATE()) eta, user_privileges.level level, user_privileges.description FROM users "
			+ "INNER JOIN user_privileges on user_privileges.id = users.privilege_id "
			+ "WHERE username = #{username} LIMIT 0,1")
	User getUserByUsername(String username);

	@Update("UPDATE user_addresses SET residence = 1 WHERE id = #{id} AND user_id = #{user_id}")
	Integer setResidence(Integer user_id, Integer id);

	@Update("UPDATE user_addresses SET residence = 0 AND user_id = #{user_id}")
	Integer removeResidence(Integer user_id);

	@Update("UPDATE users SET "
			+ "first_name = #{firstName}, last_name = #{lastName}, dob = #{dob}, sex = #{sex}, telephone = #{telephone} "
			+ "WHERE id = #{id}")
	Integer updateUser(User user);

	///////////////////////// START ADDRESSES ///////////////////////

	@Select("SELECT id, CAP, user_id userId, street, number, residence FROM user_addresses WHERE user_id = #{user_id} "
			+ "ORDER BY residence DESC")
	List<Address> getUserAddressesById(Integer user_id);

	@Select("SELECT user_addresses.id, CAP, user_id userId, street, number, residence" + "FROM user_addresses "
			+ "INNER JOIN users ON user_addresses.user_id = users.id " + "WHERE users.username = #{username}"
			+ "ORDER BY residence DESC")
	List<Address> getUserAddressesByUsername(String username);

	///////////////////////// END ADDRESSES ///////////////////////

	@Select("SELECT * FROM user_privileges " + "WHERE description = #{description} ")
	Privilege getPrivilege(String description);

	///////////////////////// START SEARCH ///////////////////////
	@Select("SELECT count(id) res FROM users WHERE privilege_id = (select id from user_privileges where description = 'USER')")
	Integer getUsersCount();

	@Select("SELECT count(id) FROM users " + "WHERE id = #{id} "
			+ "OR (#{id} = '' AND username LIKE '%' #{username} '%' "
			+ "AND CONCAT(first_name,' ', last_name) LIKE '%' #{name} '%' )"
			+ "AND privilege_id = (select id from user_privileges where description = 'USER')")
	Integer getUsersCountByField(UserSearch search);

	@Select("SELECT users.id, users.first_name as firstName, users.last_name as lastName"
			+ ", users.username, users.dob, users.sex, users.telephone, users.privilege_id "
			+ "FROM users "
			+ "WHERE id = #{id} " + "OR (#{id} = '' AND username LIKE '%' #{username} '%' "
			+ "AND CONCAT(first_name,' ', last_name) LIKE '%' #{name} '%') "
			+ "AND privilege_id = (select id from user_privileges where description = 'USER') "
			+ "ORDER BY ${sortBy} ${sort}")
	List<User> getUsersByField(RowBounds rowBounds, UserSearch search);

	@Select("SELECT users.id, users.first_name as firstName, users.last_name as lastName"
			+ ", users.username, users.dob, users.sex, users.telephone, users.privilege_id "
			+ " FROM users "
			+ "WHERE privilege_id = (select id from user_privileges where description = 'USER') "
			+ "AND (id LIKE '%' #{search} '%' OR username LIKE '%' #{search} '%' "
			+ "OR CONCAT(first_name,' ', last_name) LIKE '%' #{search} '%')")
	List<User> getUsersByKeyword(RowBounds rowBounds, String search);

	@Select("SELECT count(id) FROM users "
			+ "WHERE privilege_id = (select id from user_privileges where description = 'USER') "
			+ "AND (id LIKE '%' #{search} '%' " + "OR username LIKE '%' #{search} '%' "
			+ "OR CONCAT(first_name,' ', last_name) LIKE '%' #{search} '%')")
	Integer getUsersCountByKeyword(String search);

	@Select("SELECT id, first_name as firstName, last_name as lastName, username FROM users "
			+ "WHERE privilege_id = (select id from user_privileges where description = 'USER') ")
	List<User> getUsernames();

	///////////////////////// END SEARCH ///////////////////////

	///////////////////////// START SKILLS ///////////////////////
	@Select("SELECT skills.id, skills.skill, users_skills.value" + "FROM users_skills "
			+ "INNER JOIN skills ON users_skills.skill_id = skills.id " + " WHERE user_id = #{user_id} ")
	List<Skill> getUserSkillsById(Integer user_id);

	@Select("SELECT skills.id, skills.skill, users_skills.value FROM users_skills "
			+ "INNER JOIN users ON users_skills.user_id = users.id "
			+ "INNER JOIN skills ON users_skills.skill_id = skills.id " + " WHERE username = #{username} ")
	List<Skill> getUserSkillsByUsername(String username);

	//////////////////////// END SKILLS ///////////////////////////

}
