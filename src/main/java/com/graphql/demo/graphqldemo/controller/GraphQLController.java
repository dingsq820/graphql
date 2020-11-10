package com.graphql.demo.graphqldemo.controller;

import java.util.LinkedHashMap;
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

//		DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
//		dataLoaderRegistry.register("authorLoader", dataLoaderInit.initDataLoaderRegistry());
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
				.dataLoaderRegistry(dataLoaderInit.initDataLoaderRegistry()).operationName(operationName).variables(variables).build();

//		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
//				.operationName(operationName).variables(variables).build();
		ExecutionResult executionResult = graphql.execute(executionInput);
		// ----start----
//		Publisher<ExecutionResult> newBookAddStream = executionResult.getData();
//		AtomicReference<Subscription> atomicReference = new AtomicReference<>();
//		newBookAddStream.subscribe(new Subscriber<ExecutionResult>() {
//
//			@Override
//			public void onSubscribe(Subscription s) {
//
//				atomicReference.set(s);
//				s.request(1);
//
//			}
//
//			@Override
//			public void onNext(ExecutionResult t) {
//				// TODO
//
//				atomicReference.get().request(1);
//
//			}
//
//			@Override
//			public void onError(Throwable t) {
//
//				System.out.println("Subscription threw an exception" + t.toString());
//				
//
//			}
//
//			@Override
//			public void onComplete() {
//				System.out.println("Subscription Complete");
//
//			}
//		});
		// ----end----
		Map<String, Object> resultMap = executionResult.toSpecification();
		if (resultMap.containsKey("extensions")) {
			resultMap.remove("extensions");
		}
		return resultMap;
	}

}