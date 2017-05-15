//user registration start
function check()
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var numbers = /^[0-9]+$/;
    var letters = /^[a-zA-Z]+$/;
    var usecase = /^[0-9a-zA-Z]+$/;
    var x = document.forms["form1"]["username"].value;
//var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
    if (document.getElementById("userid").value == "")
    {
        alert("Username must not be empty");
        document.form1.userid.focus();
        return false;
//document.getElementById('in').getAttributeNode('class').value="correct";
    }

    else if (!document.getElementById("userid").value.match(usecase))
    {
        alert("Invalid User name");
        document.form1.userid.focus();
        return false;
    }

    else if (document.getElementById("passid").value == "")
    {
        alert("Please enter Password...");
        document.form1.passid.focus();
        return false;
//document.getElementById('in').getAttributeNode('class').value="correct";
    }

    else if (document.getElementById("username").value == "")
    {
        alert("Username Must Not be Empty");
        return false;
    }

    else if (!document.getElementById("username").value.match(letters))
    {
        alert("Username Must Be Alphabets only");
        return false;
    }

    else if (document.getElementById("email").value == "")
    {
        alert("Enter an email address!");
        return false;
    }


    else if (!document.getElementById("email").value.match(mailformat))
    {
        alert("You have entered an invalid email address!");
        return false;
    }

    else if (document.getElementById("mob").value == "")
    {
        alert("Enter Mobile number...");
        document.form1.mob.focus();
        return false;
//document.getElementById('in').getAttributeNode('class').value="correct";
    }
    else if (!document.getElementById("mob").value.match(numbers))
    {
        alert("Mobile Must Be Numeric");
        document.form1.mob.focus();
        return false;
    }
    else
    {
        return true;
    }
}
//user registration end

//share files click function start 
function shareFiles()
{
    window.location.replace('sharefiles.jsp');
}
//share files click function end 

//select files by checkbox and select different users to share.

$(document).ready(function() {
    $(".imgclass").click(function() {
        var filesListArray = [];

        $("input:checked[name='my_match[]']").each(function() {
            filesListArray.push($(this).val());

        });
        // alert('files list array: ' + filesListArray);
        var userListArray = [];

        $("input:checked[name='my_match1[]']").each(function() {
            userListArray.push($(this).val());

        });
        //alert('users list array: ' + userListArray);



        var dataString = 'filesListArray=' + filesListArray + '&userListArray=' + userListArray;
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "dosharefiles.jsp",
            data: dataString,
            cache: false,
            success: function(response)
            {

                var get = response.toString().indexOf("success");
                if (get !== -1)
                {
                    alert('Files are shared successfully..');
                }
                else
                {
                    alert('Try Again.. Files are not shared successfully.. ');
                }

            }

        });

    });
});


//open file and read and write
function openFile(filePath, user, extension)
{
    var dataString = 'filePath=' + filePath + '&user=' + user+ '&extension=' + extension;
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "readandwrite.jsp",
        data: dataString,
        cache: false,
        success: function(response)
        {
            //text show
            if (extension === "txt")
            {
                var get = response.toString().indexOf("line: ");
                if (get !== -1)
                {

                    document.getElementById('textA').innerHTML = response.substring(get + 5, response.indexOf("<--end-->"));
                    document.getElementById('saveFile').innerHTML = filePath;
                    //$('#saveFile').innerHTML = filePath;
                }
                else
                {
                    alert('Sorry! Try again... ');

                }
            }
            //docx show 
            
            else if (extension === "docx")
            {
                var get = response.toString().indexOf("line: ");
                if (get !== -1)
                {

                    document.getElementById('textA').innerHTML = response.substring(get + 5, response.indexOf("<--end-->"));
                    document.getElementById('saveFile').innerHTML = filePath;
                    // $('#saveFile').innerHTML = filePath;
                }
                else
                {
                    alert('Sorry! Try again... ');

                }
            }


            //pdf show 
            else if (extension === "pdf")
            {
                var get = response.toString().indexOf("line: ");
                if (get !== -1)
                {
                    document.getElementById('textA').innerHTML = response.substring(get + 5, response.indexOf("<--end-->"));
                    document.getElementById('saveFile').innerHTML = filePath;
                    //$('#saveFile').innerHTML = filePath;
                }
                else
                {
                    alert('Sorry! Try again... ');

                }
            }
         
      
            else
            {
                alert('Sorry! Your file extension cannot be readable. Only .txt, .doc and .pdf files accepted.. ');

            }


        },
        error: function(request, status, errorThrown) {
            //alert(request+" "+ status+" "+ errorThrown);
            console.log(request + " " + status + " " + errorThrown);
        }

    });
}

//file extension
function getExt(filename)
{
    var ext = filename.split('.').pop();
    if (ext == filename)
        return "";
    return ext;
}


//save the edited files

function saveFile(user)
{
    var textAreaContect = $("#textA").val();
    var saveFile = $('#saveFile').val();
   // var extension = getExt(saveFile);
    var dataString = 'filePath=' + saveFile + '&user=' + user + '&textAreaContect=' + textAreaContect;
    event.preventDefault();
    $.ajax({
        type: "POST",
        url: "saveFile.jsp",
        data: dataString,
        cache: false,
        success: function(response)
        {
            var $response = $(response);
            var isUpdated = $response.filter('#isUpdated').text();

            //text show
            if (isUpdated === "true")
            {
                alert('Your file is Saved.. ');

                $('#textA').text('Your File Content Will Be Shown Here...');
                $('#saveFile').text('Your File Path Will Be Shown Here');
               // document.getElementById('saveFile').innerHTML = "";
               // $('#saveFile').innerHTML = "";
            }
            else
            {
                alert('Sorry. Try again..');
            }


        },
        error: function(request, status, errorThrown) {
            //alert(request+" "+ status+" "+ errorThrown);
            console.log(request + " " + status + " " + errorThrown);
        }
    });
}




//Audit files funtion . it's audited by tpa.

$(document).ready(function() {
    $(".auditFiles").click(function() {
        var filesListArray = [];

        $("input:checked[name='audit[]']").each(function() {
            filesListArray.push($(this).val());

        });
        // alert('files list array: ' + filesListArray);
       



        var dataString = 'filesListArray=' + filesListArray;
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "doAuditFiles.jsp",
            data: dataString,
            cache: false,
            success: function(response)
            {

                //var get = response.toString().indexOf("success");
                var report =  $($.parseHTML(response)).filter("#report");
                                 
                    $('#auditedFiles').html("<h3>Audited Files Report :<h3> <br/>");
                    $('#auditedFiles').html(report);
                   // alert('File is okay..');
               

            }

        });

    });
});