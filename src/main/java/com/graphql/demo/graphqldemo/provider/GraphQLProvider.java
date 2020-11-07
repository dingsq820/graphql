package com.graphql.demo.graphqldemo.provider;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.SchemaParser;
import com.graphql.demo.graphqldemo.resolver.BookResolver;
import com.graphql.demo.graphqldemo.resolver.MutationResolver;
import com.graphql.demo.graphqldemo.resolver.QueryResolver;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@Component
public class GraphQLProvider {

	private GraphQL graphQL;

	@Autowired
	private QueryResolver queryResolver;

	@Autowired
	private BookResolver bookResolver;
	
	@Autowired
	private MutationResolver mutationResolver;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	@PostConstruct
	public void init() throws IOException {

		GraphQLSchema schema = SchemaParser.newParser().file("schema/schema.graphql")
				.resolvers(queryResolver, bookResolver,mutationResolver).build().makeExecutableSchema();

		this.graphQL = GraphQL.newGraphQL(schema).build();

	}

}