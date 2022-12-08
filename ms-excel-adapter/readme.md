<h1>Ms Excel Adapter</h1>

<p>
This service is adapter intended to work with excel files.
</p>

<h3>1. SecurityConfig</h3>
<p>
This class adds <strong>authorization</strong> and <strong>authentication</strong> filters to the service context 
and identifies which endpoints are accessible for which requests.
</p>

<h3>2. FileController</h3>
<h5>4.1. FILES_URL [POST]</h5>
<p>
This endpoint accepts FileModel as request body and creates new excel file with relative data.
</p>

<h5>4.2. FILES_URL / {file-name} [PUT]</h5>
<p>
This endpoint finds file with name from <code>file-name</code> variable and adds new sheet into it with relative data 
from request body.
</p>



<h3>3. ExcelAdapterException</h3>
<p>
Generic exception for whole service
</p>

<h3>4. GlobalExceptionHandler</h3>
<p>
Exception handler for edge cases. Handler catches thrown exceptions and returns <code>ErrorResponseModel</code> with 
relative date.
</p>

<h3>5. AuthorizationFilter</h3>
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

<h3>6. FileService</h3>
<p>Service class for File related operations</p>

<h5>createFile()</h5>
<p>
This method creates excel file with data provided in <code>FileModel</code>.
</p>

<h5>addSheet()</h5>
<p>
This method finds excel file with given name and adds new excel sheet into it.
</p>
