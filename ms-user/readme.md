<h1>Ms User</h1>

<p>
This service contains all <strong>user-related</strong> operations.
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

<h3>3. UserController</h3>
<h5>3.1. SIGNUP_URL</h5>
<p>
This endpoint accepts SignupRequestModel as request body and creates new user with relative data.
</p>

<h3>4. User</h3>
<p>Entity class of User table</p>

<h3>5. Role</h3>
<p>Entity class of Role table</p>

<h3>6. AppException</h3>
<p>
Generic exception for whole service
</p>

<h3>7. GlobalExceptionHandler</h3>
<p>
Exception handler for edge cases. Handler catches thrown exceptions and returns <code>ErrorResponseModel</code> with 
relative date.
</p>

<h3>8. UserMapper </h3>
<p>
Utility class to map data between <code>User</code> based classes. This class is provided by 
<strong>Mapstruct</strong> library.
</p>

<h3>9. UserRepository</h3>
<p>JpaRepository class for <code>User</code> entity</p>

<h3>10. RoleRepository</h3>
<p>JpaRepository class for <code>Role</code> entity</p>

<h3>11. AuthorizationFilter</h3>
<p>
Authorization filter for security context
</p>

<h5>doFilterInternal()</h5>
<p>
This method is triggered when any request is sent to service.
It checks <code>X-Gateway-Token</code> to ensure request is not sent directly to service but through 
gateway. 

- if token is not valid, endpoint  returns <code>ErrorResponseModel</code> with <strong>Invalid 
credentials</strong> message.

- if token is valid user is authorized by <strong>JWT</strong> provided via header to check whether user is allowed to 
  access relative endpoint.
</p>

<h3>12. UserService</h3>
<p>Service class for User related operations</p>
