<#import "parts/common_start.ftl" as c>

<@c.page>
<h5>Hello, user</h5>
<div>This is a program Happy Tooth</div>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Log In</button>
    </form>
</div>
</@c.page>