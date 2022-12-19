<ul>
<li>This project is a simple implementation of a simple stock-management system.</li>
</ul>
<p>&nbsp;&nbsp;<strong>Technologies in using :</strong></p>
<ul>
<ul>
<li>java 11</li>
<li>spring boot</li>
<li>maven</li>
</ul>
<li><strong>Database :</strong></li>
<ul>
<li>MySQL</li>
</ul>
<li><strong>Libraries :</strong></li>
<ul>
<li><strong>lombok</strong>: for reduce boilerplate code for model/data objects.</li>
<li><strong>Swagger:&nbsp;</strong>for helps users build, document, test and consume RESTful web services<strong>.</strong></li>
<li><strong>ModelMapper</strong>: To make object mapping easy, by automatically determining how one object model maps to another, based on conventions.</li>
</ul>
</ul>
<ul>
<li><strong>Javafaker: </strong>For create initial list of sample data</li>
</ul>
<ul>
<ul>
</ul>
</ul>
<p>&nbsp;</p>
<ul>
<li><strong>Entry Point</strong>
<ul>
<li><strong>Swagger&nbsp;</strong></li>
</ul>
</li>
</ul>
<p>After running the app the best entry point is&nbsp;<strong>Swagger&nbsp;</strong>and the url is</p>
<p>&nbsp;<u><a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a></u>&nbsp;</p>
<p>&nbsp;You can see the APIs and you can test them. Also after run the app you can test it with Postman or other client Apps</p>
<ul>
<li><strong>Configuration</strong>
<ul>
<li><strong>Disabling/Enabling create init data:</strong></li>
</ul>
</li>
<li>Go to properties file</li>
<li>Look for property <em>stock-table.create-sample-data.status</em></li>
<li>Set it to on/off</li>
</ul>
<p><strong>Note: </strong>Before the first run set this property to <strong><em>on. </em></strong>The init data will be created. Then set it to <strong><em>off</em></strong><em>. </em>So in other runs the init data will not be created again.</p>
<ul>
<li><strong>Number of rows in create init data:</strong>
<ul>
<li>Go to properties file</li>
<li>Look for property <em>stock-table.create-sample-data.row-count</em></li>
<li>Set it</li>
</ul>
</li>
</ul>
<p>&nbsp;</p>
<ul>
<li><strong>Profiles:</strong></li>
</ul>
<p>There are three profiles for the database connection which are defined in application.yml file:</p>
<ul>
<li><strong>dev-localdb</strong>:for database in local system for development.&sect;&nbsp;</li>
</ul>
<ul>
<li><strong>dev-dockerdb</strong>: for database in docker container for development.&sect;&nbsp;</li>
<li><strong>prod</strong>: for database in production.&sect;&nbsp; &nbsp;&nbsp;</li>
</ul>
<p><strong>Note: </strong>If you want to you the <strong>dev-localdb </strong>profile, you should run the docker-compose file with command &ldquo;docker-compose up&rdquo;</p>
<p><strong>Note: </strong>The default profile is <strong>dev-localdb</strong></p>