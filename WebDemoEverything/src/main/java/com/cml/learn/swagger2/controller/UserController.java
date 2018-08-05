package com.cml.learn.swagger2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserController {

	// 创建线程安全的Map
	static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

	/**
	 * 获取用户全部信息
	 * 
	 * @param u
	 * @param delFlg
	 * @return
	 */
	@ApiOperation(value = "获取用户全部信息", notes = "获取当前在线的全部用户信息，注意是在线的哦！！！")
	@ApiImplicitParams({ @ApiImplicitParam(name = "u", value = "需要的用户信息", required = true, dataType = "User", paramType = "body") })
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	@ResponseBody
	public List<User> getUserList(@RequestBody User u) {
		System.out.println("获取全部数据！！！");
		return Arrays.asList(users.values().toArray(new User[] {}));
	}

	/**
	 * 根据ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserById(@PathVariable(value = "id") Integer id) {
		JsonResult r = new JsonResult();
		try {
			User user = users.get(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public ResponseEntity<JsonResult> getUserList() {
		JsonResult r = new JsonResult();
		try {
			Thread.sleep(500);
			List<User> userList = new ArrayList<User>(users.values());
			r.setResult(userList);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add(@RequestBody User user) {
		JsonResult r = new JsonResult();
		try {
			users.put(user.getId(), user);
			r.setResult(user.getId());
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "根据url的id来指定删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete(@PathVariable(value = "id") Integer id) {
		JsonResult r = new JsonResult();
		try {
			users.remove(id);
			r.setResult(id);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 根据id修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "更新信息", notes = "根据url的id来指定更新用户信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User") })
	@RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<JsonResult> update(@PathVariable("id") Integer id, @RequestBody User user) {
		JsonResult r = new JsonResult();
		try {
			User u = users.get(id);
			u.setUsername(user.getUsername());
			u.setAge(user.getAge());
			users.put(id, u);
			r.setResult(u);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");

			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	@ApiIgnore // 使用该注解忽略这个API
	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	public String jsonTest() {
		return " hi you!";
	}
}