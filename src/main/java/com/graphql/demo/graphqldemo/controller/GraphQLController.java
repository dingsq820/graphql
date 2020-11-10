package com.graphql.demo.graphqldemo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.demo.graphqldemo.dataLoader.AuthorLoader;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

/**
 * GraphQL Controller
 *
 */
@RestController
@RequestMapping("/graphql")
public class GraphQLController {

	@Autowired
	private AuthorLoader authorLoader;

	private final GraphQL graphql;

	@Autowired
	public GraphQLController(GraphQL graphql) {
		this.graphql = graphql;
	}

	/**
	 * POST METHOD
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping
	public Map<String, Object> graphqlPOST(@RequestBody Map<String, Object> body) {
		String query = (String) body.get("query");
		if (query == null) {
			query = "";
		}
		String operationName = (String) body.get("operationName");
		@SuppressWarnings("unchecked")
		Map<String, Object> variables = (Map<String, Object>) body.get("variables");
		if (variables == null) {
			variables = new LinkedHashMap<>();
		}

		return executeGraphqlQuery(query, operationName, variables);
	}

	/**
	 * GraphQL Execute
	 *
	 * @param query         Query
	 * @param operationName OperationName
	 * @param variables     Variables
	 * @return ResultMap
	 */
	private Map<String, Object> executeGraphqlQuery(String query, String operationName, Map<String, Object> variables) {

		DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
		dataLoaderRegistry.register("authorLoader", authorLoader.dataLoader);
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
				.dataLoaderRegistry(dataLoaderRegistry).operationName(operationName).variables(variables).build();

//		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
//				.operationName(operationName).variables(variables).build();
		ExecutionResult executionResult = graphql.execute(executionInput);
		Map<String, Object> resultMap = executionResult.toSpecification();
		if (resultMap.containsKey("extensions")) {
			resultMap.remove("extensions");
		}
		return resultMap;
	}

}