<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Admin panel</h1><hr>
<table class="table table-hover table-dark">
    <tr>
        <th>Email</th>
        <th>Active</th>
        <th>Role</th>
        <th>Ban</th>
        <th>Change</th>
        <th>Information</th>

    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}<#sep>, </#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Ban">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Change</a></th>
            <th><a href="/user/${user.id}">Information</a></th>
        </tr>
        <#else>
        <h3>Not founded user</h3>
    </#list>
</table>

</@t.template>