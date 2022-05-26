package com.cat.solar.es.config;

import org.apache.commons.httpclient.HostConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class SecurityConfig extends ResourceServerConfigurerAdapter {

              /*
              * @Override public void configure(ResourceServerSecurityConfigurer resources) {
              * System.out.println("In security config url.."); resources.tokenStore(new
              * JwkTokenStore(
              * https://cwslogin.b2clogin.com/cwslogin.onmicrosoft.com/discovery/v2.0/keys?p=B2C_1A_P1_V1_SignIn_Staging
              * )) //Update {policy} with the sign in policy being used in the frontend
              * .resourceId("ffff6bb7-1f83-49a1-9b16-fafd2901a686"); //Update {client-id}
              * with the client id that is being used for the frontend application
              * 
               * }
              */

              @Override
              public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
              //resources.tokenServices(tokenServices()).resourceId("87c7170e-41da-4b33-9ce3-63c1fdafd714");
                            resources.tokenServices(tokenServices()).resourceId("ffff6bb7-1f83-49a1-9b16-fafd2901a686");
              
              }



              @Bean
              @Primary
              public DefaultTokenServices tokenServices() throws Exception {
              DefaultTokenServices tokenServices = new DefaultTokenServices();
              tokenServices.setTokenStore(tokenStore());
              return tokenServices;
              }



              @Bean
              public TokenStore tokenStore() throws Exception {
                             //return new JwkTokenStore(https://cwslogin.b2clogin.com/cwslogin.onmicrosoft.com/discovery/v2.0/keys?p=B2C_1A_P1_V1_SignIn_Prod);
                             return new JwkTokenStore(https://cwslogin.b2clogin.com/cwslogin.onmicrosoft.com/discovery/v2.0/keys?p=B2C_1A_P1_V1_SignIn_Staging);
              }
              
              @Override
              public void configure(HttpSecurity http) throws Exception {
                             HostConfiguration hostconfig = new HostConfiguration();
                             hostconfig.setProxy("proxy.cat.com", 80);
                             System.out.println("In security config proxy..");
                             
              http.requiresChannel().anyRequest().requiresSecure().and().antMatcher("/**").authorizeRequests().anyRequest()
                                                          .authenticated();
                             http.cors();
                             
              }
}
