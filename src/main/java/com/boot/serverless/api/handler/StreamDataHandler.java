package com.boot.serverless.api.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.boot.serverless.api.ApiApplication;

public class StreamDataHandler implements RequestStreamHandler {

	public static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(ApiApplication.class, "dev");
		} catch(ContainerInitializationException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		// TODO Auto-generated method stub
		handler.proxyStream(input, output, context);
		output.close();
	}

}
