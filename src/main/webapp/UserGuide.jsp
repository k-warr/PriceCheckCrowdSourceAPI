<style>table, tr, th, td {border: 1px solid black; padding: .2em;} </style>
<html>
<body>
<div>
    <h1>User Guide</h1>
    <p>This web service version 1 takes one request at a time</p>
    <h3>Query Parameters</h3>
    <p>Each of the parameters below are required to get a price</p>
    <table>
        <tr>
            <th>Parameter</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>name</td>
            <td>This is the grocery item name or designation</td>
        </tr>
        <tr>
            <td>brand</td>
            <td>This is the brand of the grocery item</td>
        </tr>
        <tr>
            <td>lon</td>
            <td>This is the longtitude of the location of the user in radians</td>
        </tr>
        <tr>
            <td>lat</td>
            <td>This is the latitude of the location of the user in radians</td>
        </tr>
        <tr>
            <td>distance</td>
            <td>This is the radius in miles centered on the location of the user that is used to determine the circular search area for grocery stores selling the grocery item</td>
        </tr>
    </table>
    <h3>Response Field</h3>
    <table>
        <tr>
            <th>Field</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>factId</td>
            <td>This is the identifier of the price fact</td>
        </tr>
        <tr>
            <td>userId</td>
            <td>This is the identifier of the user who entered the price fact</td>
        </tr>
        <tr>
            <td>apiKey</td>
            <td>This is the key assigned to the user used in validation during posting price fact</td>
        </tr>
        <tr>
            <td>priceAmount</td>
            <td>This is the price amount entered by the user for the item</td>
        </tr>
        <tr>
            <td>storeId</td>
            <td>This is the identifier of the grocery store selling the item</td>
        </tr>
        <tr>
            <td>storeName</td>
            <td>This is the name of the grocery store selling the item</td>
        </tr>
        <tr>
            <td>latitude</td>
            <td>This is the latitude of the store address location in radians</td>
        </tr>
        <tr>
            <td>longtitude</td>
            <td>This is the longtitude of the store address location in radians</td>
        </tr>
        <tr>
            <td>storeAddress</td>
            <td>This is the address of the grocery store selling the item</td>
        </tr>
        <tr>
            <td>itemId</td>
            <td>This is the identifier of the item requested for pricing</td>
        </tr>
        <tr>
            <td>itemName</td>
            <td>This is the item requested for pricing</td>
        </tr>
        <tr>
            <td>unit</td>
            <td>This is the unit measure the item is sold by e.g. ounce, pounds, box, pieces</td>
        </tr>
        <tr>
            <td>unitValue</td>
            <td>This is the quantity in unit designation of the item</td>
        </tr>
        <tr>
            <td>brandId</td>
            <td>This is the brand of the item in this price fact</td>
        </tr>

        <tr>
            <td>factDateTime</td>
            <td>This is the date and time the user posted the price fact for the item</td>
        </tr>
    </table>
    <h3>Error Codes</h3>
    <p>The following are the error codes</p>
    <table>
        <tr>
            <th>Error Code</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>400</td>
            <td>Bad Request - the request made is not valid</td>
        </tr>
        <tr>
            <td>404</td>
            <td>Not Found - the request made is not found</td>
        </tr>
        <tr>
            <td>500</td>
            <td>Internal Error - internal error request not completed</td>
        </tr>
        <tr>
            <td>503</td>
            <td>Service Unavailable - service is not available</td>
        </tr>
    </table>
    <h3>Web Service Usage</h3>
    <p>The following are the different API available in this service</p>
    <table>
        <tr>
            <th>Action</th>
            <th width="450">URI</th>
            <th>Description</th>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/HTML/request</td>
            <td>This returns the item prices based on parameters given in HTML format e.g. <br>
                http://localhost:8080/pricerequest/HTML/request?name=Ketchup&brand=Huntz</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/JSON/request</td>
            <td>This returns the item prices based on parameters given in JSON format e.g. <br>
                http://localhost:8080/pricerequest/HTML/request?name=Ketchup&brand=Huntz</td>
        </tr>

        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/HTML/items</td>
            <td>This returns all the items in the database in HTML format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/HTML/stores</td>
            <td>This returns all the stores in the database in HTML format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/HTML/brands</td>
            <td>This returns all the brands in the database in HTML format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/JSON/items</td>
            <td>This returns all the items in the database in JSON format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/JSON/stores</td>
            <td>This returns all the stores in the database in JSON format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>http://localhost:8080/pricerequest/JSON/brands</td>
            <td>This returns all the brands in the database in JSON format. This can be used in pull down menus</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>http://localhost:8080/pricerequest/HTML/newuser</td>
            <td>This add a new user to the database. This returns the apiKey assigned to the user for the purpose of validation in posting price fact</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>http://localhost:8080/pricerequest/JSON/newuser</td>
            <td>This add a new user to the database. This returns the apiKey in JSON format assigned to the user for the purpose of validation in posting price fact</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>http://localhost:8080/pricerequest/HTML/create</td>
            <td>This adds a new price fact in the database using the form parameters similar to the response fields listed above. If the item entered is not found, the item is added as a new item. If the brand is not found, the brand is added as a new brand. If the store is not found, the store is added as a new store. A message is returned if the pricefact was added successfully or encountered an issue with the request in HTML format</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>http://localhost:8080/pricerequest/JSON/create</td>
            <td>This adds a new price fact in the database using the form parameters similar to the response fields listed above. If the item entered is not found, the item is added as a new item. If the brand is not found, the brand is added as a new brand. If the store is not found, the store is added as a new store. A message is returned if the pricefact was added successfully or encountered an issue with the request in HTML format</td>
        </tr>

    </table>

</div>
</body>
</html>