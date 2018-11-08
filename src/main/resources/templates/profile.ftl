<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<h5>${username}</h5>
${message?ifExists}
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Name:</label>
        <div class="col-sm-6">
            <input type="text" name="name" class="form-control" placeholder="Name"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">SurName:</label>
        <div class="col-sm-6">
            <input type="text" name="surName" class="form-control" placeholder="SurName"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Address:</label>
        <div class="col-sm-6">
            <input type="text" name="address" class="form-control" placeholder="Address"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Age:</label>
        <div class="col-sm-6">
            <input type="text" name="age" class="form-control" placeholder="Age"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Mobile:</label>
        <div class="col-sm-6">
            <input type="text" name="mobile" class="form-control" placeholder="Mobile"/>
        </div>
    </div>
    <#if isDoctor>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">QualificationDoctor:</label>
        <div class="col-sm-6">
            <input type="text" name="qualificationDoctor" class="form-control" placeholder="QualificationDoctor"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">CategoryDoctor:</label>
        <div class="col-sm-6">
            <input type="text" name="categoryDoctor" class="form-control" placeholder="CategoryDoctor"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">ExperienceDoctor:</label>
        <div class="col-sm-6">
            <input type="text" name="experienceDoctor" class="form-control" placeholder="ExperienceDoctor"/>
        </div>
    </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Save</button>
</form>
</@c.page>