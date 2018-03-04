package com.example.DemoGraphQL.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowNotFoundException extends RuntimeException implements GraphQLError {

  private Map<String, Object> extensions = new HashMap<>();

  public ShowNotFoundException(String message, Long invalidShowId) {
    super(message);
    extensions.put("invalidShowId", invalidShowId);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DataFetchingException;
  }
}
