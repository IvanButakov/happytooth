<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<#if isDoctor>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by purpose of the visit" />
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>
</#if>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new purpose
</a>
<div class="collapse <#if registration??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post">
            <div class="form-group">
                <input type="text" class="form-control ${(reasonError??)?string('is-invalid', '')}"
                       value="<#if registration??>${registration.reason}</#if>" name="reason" placeholder="Enter the purpose of the visit" />
                <#if reasonError??>
                <div class="invalid-feedback">
                    ${reasonError}
                </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
              <button type="submit" class="btn btn-primary">Make an appointment</button>
            </div>
        </form>
    </div>
</div>

<#if isDoctor>
<table class="table mt-3" id="registration-list">
    <thead class="bg-primary">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Purpose of the visit</th>
        <th scope="col">Time of registration</th>
        <th scope="col">Last name, First name</th>
        <th scope="col">Address</th>
        <th scope="col">Age</th>
        <th scope="col">Phone</th>
        <th scope="col">Email</th>
        <th scope="col">Informing by phone</th>
        <th scope="col">Data and time of the visit</th>
    </tr>
    </thead>
    <tbody>
    <#list registrations as registration>
    <tr>
        <th scope="row">${registration.id}</th>
        <td>${registration.reason}</td>
        <td>${registration.time}</td>
        <td>${registration.authorName}</td>
        <td>${registration.authorAddress}</td>
        <td>${registration.authorAge}</td>
        <td>${registration.authorMobile}</td>
        <td>${registration.authorEmail}</td>
        <td>${registration.phone}</td>
        <td>${registration.timeVisit}</td>
    </tr>
    <#else>
    No registration
    </#list>
    </tbody>
</table>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample2">
    Send the letter to the client
</a>
<div class="collapse" id="collapseExample2">
    <div class="form-group mt-3">
        <form method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="email" placeholder="Enter client's email address" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="phone" placeholder="Enter whether the client was informed by phone" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="idRegistration" placeholder="Enter a registration number" />
                </div>
                <div class="form-group">
                    <input type="date" class="form-control" name="timeVisit" placeholder="Enter the date and time of the visit" />
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Send</button>
                </div>
        </form>
    </div>
</div>
</#if>
</@c.page>