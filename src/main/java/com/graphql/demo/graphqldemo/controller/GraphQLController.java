package com.graphql.demo.graphqldemo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphql.demo.graphqldemo.dataLoader.DataLoaderInit;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.validation.ValidationError;

/**
 * GraphQL Controller
 *
 */
@RestController
@RequestMapping("/graphql")
public class GraphQLController {

	@Autowired
	private DataLoaderInit dataLoaderInit;

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

		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
				.dataLoaderRegistry(dataLoaderInit.initDataLoaderRegistry()).operationName(operationName)
				.variables(variables).build();

//		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
//				.operationName(operationName).variables(variables).build();
		ExecutionResult executionResult = graphql.execute(executionInput);

		Map<String, Object> resultMap = executionResult.toSpecification();
		if (resultMap.containsKey("extensions")) {
			resultMap.remove("extensions");
		}
		List<GraphQLError> errors = executionResult.getErrors();
		return resultMap;
	}

}