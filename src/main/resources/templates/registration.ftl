<#import "parts/common.ftl" as c>
<#import "parts/registration.ftl" as r>

<@c.page>
<div class="mb-1">Add new user</div>
${message?ifExists}
<@r.registration "/registration" />
</@c.page>