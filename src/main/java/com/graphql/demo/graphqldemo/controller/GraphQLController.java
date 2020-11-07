package com.graphql.demo.graphqldemo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.demo.graphqldemo.dao.BookDao;

import graphql.ExecutionInput;
import graphql.GraphQL;

@CrossOrigin
@RestController
@RequestMapping("/graphql")
public class GraphQLController {

	@Autowired
	BookDao bookDao;

	private final GraphQL graphql;
	private final ObjectMapper objectMapper;

	@Autowired
	public GraphQLController(GraphQL graphql, ObjectMapper objectMapper) {
		this.graphql = graphql;
		this.objectMapper = objectMapper;
	}

	/**
	 * POST方式查询
	 *
	 * @param body json对象方式
	 * @return map对象
	 */
	@PostMapping
	public Map<String, Object> graphqlPOST(@RequestBody Map<String, Object> body) {
		String query = (String) body.get("query");
		if (query == null) {
			query = "";
		}
		String operationName = (String) body.get("operationName");
		Map<String, Object> variables = (Map<String, Object>) body.get("variables");
		if (variables == null) {
			variables = new LinkedHashMap<>();
		}
		return executeGraphqlQuery(query, operationName, variables);
	}

	/**
	 * 执行graphQL查询
	 *
	 * @param query         查询语句-类json字符
	 * @param operationName 查询操作名称-默认空字符
	 * @param variables     查询参数变量-map对象、默认为空map
	 * @return map对象
	 */
	private Map<String, Object> executeGraphqlQuery(String query, String operationName, Map<String, Object> variables) {
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).operationName(operationName)
				.variables(variables).build();
		return graphql.execute(executionInput).toSpecification();
	}

}