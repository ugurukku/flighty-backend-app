<h1>Flighty configuration service</h1>

<p>This service contains configurations for all other services. Instead of adding separate <code>application.
yml</code> files to all services, they are connected to config-server via <code>bootstrap.yml</code> file and all 
configurations are kept in centralized service.
</p>

<p>
<code>bootstrap.yml</code> file in config-server itself identifies locations of configuration files and how to 
connect them to related services. 
</p>