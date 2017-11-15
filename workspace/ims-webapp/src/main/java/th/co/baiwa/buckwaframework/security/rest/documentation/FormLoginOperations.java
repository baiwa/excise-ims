package th.co.baiwa.buckwaframework.security.rest.documentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;

import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

public class FormLoginOperations extends ApiListingScanner {
	
	@Autowired
	private TypeResolver typeResolver;
	
	@Autowired
	public FormLoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager) {
		super(apiDescriptionReader, apiModelReader, pluginsManager);
	}
	
	@Override
	public Multimap<String, ApiListing> scan(ApiListingScanningContext context) {
		
		Set<String> operationTagNameSet = new HashSet<String>();
		operationTagNameSet.add("authen-rest-controller");
		
		List<Operation> operationList = new ArrayList<Operation>();
		operationList.add(loginDocument(operationTagNameSet));
		operationList.add(logoutDocument(operationTagNameSet));
		
		List<ApiDescription> apiDescList = new LinkedList<ApiDescription>();
		apiDescList.add(new ApiDescription("/api/security/login", "Authentication documentation", operationList, false));
		
		Set<Tag> apiTagSet = new HashSet<Tag>();
		apiTagSet.add(new Tag("authen-rest-controller", "Login Rest Controller"));
		
		Multimap<String, ApiListing> apiListingMap = super.scan(context);
		apiListingMap.put("authentication", new ApiListingBuilder(context.getDocumentationContext().getApiDescriptionOrdering())
			.apis(apiDescList)
			.description("Authentication API")
			.tags(apiTagSet)
			.build()
		);
		
		return apiListingMap;
	}
	
	private Operation loginDocument(Set<String> operationTagNameSet) {
		Set<ResponseMessage> responseMessageSet = new HashSet<ResponseMessage>();
		
		// Status 200, Login Success
		responseMessageSet.add((new ResponseMessageBuilder())
			.code(HttpStatus.OK.value())
			.message("Login Success")
			.build()
		);
		
		// Status 401, Unauthorized
		responseMessageSet.add((new ResponseMessageBuilder())
			.code(HttpStatus.UNAUTHORIZED.value())
			.message("Login Failed")
			.build()
		);
		
		return new OperationBuilder(new CachingOperationNameGenerator())
			.method(HttpMethod.POST)
			.uniqueId("login")
			.parameters(Arrays.asList(
				new ParameterBuilder()
					.name("username")
					.description("The username")
					.parameterType("query")
					.type(typeResolver.resolve(String.class))
					.modelRef(new ModelRef("string"))
					.build(),
				new ParameterBuilder()
					.name("password")
					.description("The password")
					.parameterType("query")
					.type(typeResolver.resolve(String.class))
					.modelRef(new ModelRef("string"))
					.build()
			))
			.tags(operationTagNameSet)
			.summary("Log in")
			.notes("Here you can log in")
			.responseMessages(responseMessageSet)
			.build();
	}
	
	private Operation logoutDocument(Set<String> operationTagNameSet) {
		Set<ResponseMessage> responseMessageSet = new HashSet<ResponseMessage>();
		
		// Status 200, Logout Success
		responseMessageSet.add((new ResponseMessageBuilder())
			.code(HttpStatus.OK.value())
			.message("Logout Success")
			.build()
		);
		
		return new OperationBuilder(new CachingOperationNameGenerator())
			.method(HttpMethod.DELETE)
			.uniqueId("logout")
			.tags(operationTagNameSet)
			.summary("Log out")
			.notes("Here you can log out")
			.responseMessages(responseMessageSet)
			.build();
	}
	
}
