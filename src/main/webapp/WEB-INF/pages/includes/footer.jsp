<%@include file="empty-col-2.jsp" %>
<div class="col-md-8">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-5 column"></div>
                <div class="col-md-2 column">
                    <p>
                        <a class="btn" data-toggle="modal" data-target="#dietTrackerModal"><spring:message code="text.Title"/> - Edinburgh</a>
                    </p>
                </div>
                <div class="col-md-5 column"></div>
            </div>
        </div>
    </nav>
</div>
<%@include file="empty-col-2.jsp" %>

<div class="modal fade" id="dietTrackerModal" tabindex="-1" role="dialog" aria-labelledby="dietTrackerModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="dietTrackerModalLabel"><spring:message code="text.Title"/></h4>
            </div>
            <div class="modal-body">
                <spring:message code="text.DietTrackerDescription"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="text.Close"/></button>
            </div>
        </div>
    </div>
</div>