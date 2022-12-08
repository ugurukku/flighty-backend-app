<h1>Ms Flight</h1>

<p>
This service contains all <strong>flight-related</strong> operations.
</p>

<h3>1. AuthClient</h3>
<p>
This interface is for sending request to <code>flighty-auth-ms</code>.
</p>

<h3>2. BookingClient</h3>
<p>
This interface is for sending request to <code>ms-booking</code>.
</p>

<h3>3. ExcelAdapterClient</h3>
<p>
This interface is for sending request to <code>ms-excel-adapter</code>.
</p>


<h3>4. FeignClientConfig</h3>
<p>
This class creates custom bean for <strong>Feign</strong> configuration.
</p>

<h3>5. SecurityConfig</h3>
<p>
This class adds <strong>authorization</strong> and <strong>authentication</strong> filters to the service context 
and identifies which endpoints are accessible for which requests.
</p>

<h3>6. FlightController</h3>
<h5>6.1. FLIGHTS_URL [POST]</h5>
<p>
This endpoint accepts FlightRqModel as request body and creates new flight with relative data.
</p>

<h5>6.2. FLIGHTS_URL / {PATH_ID} [PUT]</h5>
<p>
This endpoint finds flight with id from <code>PATH_ID</code> variable and updates it with relative data from request 
body.
</p>

<h5>6.3. FLIGHTS_URL [GET]</h5>
<p>
This endpoint returns list of all available flights.
</p>

<h5>6.4. FLIGHTS_URL / {PATH_ID} [GET]</h5>
<p>
This endpoint finds flight with id from <code>PATH_ID</code> variable and returns it.
</p>

<h5>6.5. FLIGHTS_URL / {PATH_ID} [DELETE]</h5>
<p>
This endpoint finds flight with id from <code>PATH_ID</code> variable and delete it.
</p>

<h3>7. Flight</h3>
<p>Entity class of Flight table</p>

<h3>8. Location</h3>
<p>Entity class of Location table</p>

<h3>9. FlightException</h3>
<p>
Generic exception for whole service
</p>

<h3>10. FailedToGetSuccessfulResponseException</h3>
<p>
Exception for failure cases that occur while sending requests with <strong>Feign clients</strong>
</p>

<h3>11. FeignErrorDecoder</h3>
<p>
Exception handler for cases of unsuccessful feign requests. This class implements <code>ErrorDecoder</code> class 
provided by <strong>OpenFeign</strong> library.
</p>

<h3>12. GlobalExceptionHandler</h3>
<p>
Exception handler for edge cases. Handler catches thrown exceptions and returns <code>ErrorResponseModel</code> with 
relative date.
</p>

<h3>13. FlightMapper </h3>
<p>
Utility class to map data between <code>Flight</code> based classes. This class is provided by 
<strong>Mapstruct</strong>  library.
</p>

<h3>14. LocationMapper </h3>
<p>
Utility class to map data between <code>Location</code> based classes. This class is provided by 
<strong>Mapstruct</strong>  library.
</p>

<h3>15. FlightRepository</h3>
<p>JpaRepository class for <code>Flight</code> entity</p>

<h3>16. LocationRepository</h3>
<p>JpaRepository class for <code>Location</code> entity</p>

<h3>17. FlightExportScheduler</h3>
<p>
This scheduler is executed with frequency given in <code>scheduler.cron.export-to-file</code> property and
- finds all flights with <strong>COMPLETED</strong> status.
- writes data of those flights into excel file
- sets <code>active</code> column of those flights <strong>false</strong> in DB
</p>

<h3>18. FlightStatusScheduler</h3>
<p>
This scheduler is executed with frequency given in <code>scheduler.cron.status-update</code> property and
- finds all flights that arrival time is before current time.
- sets <code>status</code> column of those flights <strong>COMPLETED</strong> in DB
</p>

<h3>19. AuthorizationFilter</h3>
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

<h3>20. AuthService</h3>
<p>Service class for operations related with <code>flighty-auth-ms</code></p>

<h5>extractToken()</h5>
<p>
This method calls <code>extractToken()</code> method of <code>AuthClient</code> retrieve data from whole 
<code>ResponseEntity</code>.
</p>

<h3>23. BookingService</h3>
<p>Service class for operations related with <code>ms-booking</code></p>

<h5>deleteBooking()</h5>
<p>
This method calls <code>deleteBooking()</code> method of <code>BookingClient</code>.
<p>

<h3>24. ExcelAdapterService</h3>
<p>Service class for operations related with <code>ms-excel-adapter</code></p>

<h5>createFile()</h5>
<p>
This method calls <code>createFile()</code> method of <code>ExcelAdapterClient</code>.
<p>

<h5>addSheet()</h5>
<p>
This method calls <code>addSheet()</code> method of <code>ExcelAdapterClient</code>.
<p>

<h3>25. FlightService</h3>
<p>Service class for Flight related operations</p>

<h3>25. LocationService</h3>
<p>Service class for Location related operations</p>
