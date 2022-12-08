<h1>Flighty Auth Ms</h1>

<p>
This is centralized Authorization service that is intended to be used by other services.
</p>

<h3>1. AppConfig</h3>
<p>
This class is intended to keep beans that are created manually.
</p>

<h3>2. SecurityConfig</h3>
<p>
This class adds <strong>authorization</strong> and <strong>authentication</strong> filters to the service context 
and identifies which endpoints are accessible for which requests.
</p>

<h3>3. JwtController</h3>
<h5>3.1. EXTRACT_JWT_URL</h5>
<p>
This endpoint accepts JWT token as request body and returns related username and its roles as response body.
</p>

<h3>4. Role</h3>
<p>Entity class of Role table</p>

<h3>5. User</h3>
<p>Entity class of User table</p>

<h3>6. AppException</h3>
<p>
Generic exception for whole service
</p>

<h3>7. GlobalExceptionHandler</h3>
<p>
Exception handler for edge cases. Handler catches thrown exceptions and returns <code>ErrorResponseModel</code> with 
relative date.
</p>

<h3>8. UserRepository</h3>
<p>JpaRepository class for <code>User</code> entity</p>

<h3>9. AuthenticationFilter</h3>
<p>
Authentication filter for security context
</p>

<h5>attemptAuthentication()</h5>
<p>
This method gets <code>username</code> and <code>password</code> and authenticates user. 
</p>

<h5>successfulAuthentication()</h5>
<p>
This method is triggered when authentication is successful and makes login endpoint return <code>JWT</code> as 
response body.
</p>

<h5>unsuccessfulAuthentication()</h5>
<p>
This method is triggered when authentication is not successful and makes login endpoint return 
<code>ErrorResponseModel</code> with <strong>Invalid credentials</strong> message.
</p>

<h3>10. AuthorizationFilter</h3>
<p>
Authorization filter for security context
</p>

<h5>doFilterInternal()</h5>
<p>
This method is triggered when any request is sent to service.
It checks <code>X-Gateway-Token</code> to ensure request is not sent directly to service but through 
gateway. If token is not valid, endpoint  returns <code>ErrorResponseModel</code> with <strong>Invalid 
credentials</strong> message.
</p>

<h3>11. FUserDetails</h3>
<p>
This class implements <code>UserDetails</code> interface provided by <strong>Spring Security</strong> library and 
keeps authenticated user's details in security context.
</p>

<h3> 12. FUserDetailsService</h3>
<p>
This class implements <code>UserDetailsService</code> interface provided by <strong>Spring Security</strong> library 
and is used to find user by provided username.
</p>

<h3>13. JwtService</h3>
<p>Service class for JWT related operations</p>

<h5>generateToken()</h5>
<p>
This method generates JWT with provided username.
</p>

<h5>extractToken()</h5>
<p>
This method extracts JWT and gets <code>username</code> and <code>roles</code> from it.
</p>

<h3>14. UserService</h3>
<p>Service class for User related operations</p>

<h5>findAuthModelByUsername()</h5>
<p>
This method finds user by given username and returns <code>UserAuthModel</code>.
</p>