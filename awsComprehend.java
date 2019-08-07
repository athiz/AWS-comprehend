package com.amazonaws.samples;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageRequest;
import com.amazonaws.services.comprehend.model.DetectDominantLanguageResult;
import com.amazonaws.services.comprehend.model.DetectSyntaxRequest;
import com.amazonaws.services.comprehend.model.DetectSyntaxResult;

public class awsComprehend 
{
    public static void main( String[] args )
    {

        String text = "Its raining beautifully";
        
        // Create credentials using a provider chain. For more information, see
        // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
        AWSCredentialsProvider awsCreds = DefaultAWSCredentialsProviderChain.getInstance();
 
        AmazonComprehend comprehendClient =
            AmazonComprehendClientBuilder.standard()
                                         .withCredentials(awsCreds)
                                         .withRegion("us-east-1")
                                         .build();
                                         
        // Call detectDominantLanguage API
        System.out.println("Calling DetectDominantLanguage");
        DetectDominantLanguageRequest detectDominantLanguageRequest = new DetectDominantLanguageRequest().withText(text);
        DetectDominantLanguageResult detectDominantLanguageResult = comprehendClient.detectDominantLanguage(detectDominantLanguageRequest);
        detectDominantLanguageResult.getLanguages().forEach(x -> {
        System.out.println(x);
        
        
	        DetectSyntaxRequest detectSyntaxRequest = new DetectSyntaxRequest().withText(text).withLanguageCode(x.getLanguageCode());
	        DetectSyntaxResult detectSyntaxResult = comprehendClient.detectSyntax(detectSyntaxRequest);
	        detectSyntaxResult.getSyntaxTokens().forEach(y -> {
	        	System.out.println(y);
	        });
	        
	        
        });
        System.out.println("Calling DetectDominantLanguage\n");
        System.out.println("Done");
    }
}
