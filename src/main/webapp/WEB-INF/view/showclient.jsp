<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">

</head>
<body>
<div class="body">
    <div class="background">
    </div>
    <div class="header">
        <div class = "logo"><p>CLIENT MANAGEMENT SYSTEM</p></div>
        <div class="navigation">
            <div class="nav3">
                <a href="../../index.html">Home</a>
            </div>
            <div class="nav1">
                <a href="createclient.html">Create Client</a>
            </div>
            <div class="nav2">
                <a href="showclient.html">Show Client</a>
            </div>
        </div>
    </div>
    <div class="middle">
        <div class="retrieve" id = "retrieve">
            <p style="color:white;text-align:center;font-size:20px;">SHOW CLIENT INFORMATION</p>
            <form action="retrieve" method="get">
                <input type="text" name="clientId" placeholder="Enter client id"><br>
                <input type="submit" style="text-align:center;cursor:pointer;" value="show">
            </form>
            <div class="retrieveAll">
                <form action ="retrieveAll" method="get">
                    <input type="submit"  style="cursor:pointer;" value="show all">
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>