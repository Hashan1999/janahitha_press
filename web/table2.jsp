<%@page import="hibernate.Brand"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>


<table id="datatablex" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            
            
            <th></th>
            <th></th>
        </tr>
    </thead>


    <tbody>
        <%
            List<Brand> sList = (List<Brand>) request.getAttribute("sList");
            for (Brand s : sList) {
        %>

        <tr>
            <td ><%= s.getIdbrand()%></td>
            <td contenteditable="true"><%= s.getName()%></td>
          
            

            <td>
                <span ><button type="button"
                               class="btn btn-danger btn-round btn-sm my-0"  >Delete</button>
                               
                      <%--         class="btn btn-danger btn-round btn-sm my-0"  data-toggle="modal" data-target=".bs-example-modal-sm"  >Delete</button>

                    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h4 class="modal-title" id="myModalLabel2"></h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h4>warning </h4>
                                    <p>Are you sure you want to delete this item</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    
                                    <button type="button" class="btn btn-primary"  data-dismiss="modal" onclick="Delete()">yes</button>
                                </div>

                            </div>
                        </div>
                    </div>
                    --%>
                
                </span>
            </td>
            <td>
                <span ><button type="button"
                               class="btn btn-primary btn-round btn-sm my-0 " onclick="brandUpdate();">Update</button>


                </span>
            </td> 


        </tr>


        <%
            }
        %>

    </tbody>
</table>

