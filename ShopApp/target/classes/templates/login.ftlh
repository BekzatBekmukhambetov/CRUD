<#import "blocks/template.ftlh" as t>
<@t.template user>

    <div style="max-width: 500px; margin: auto; padding: 20px;">
        <h1 style="text-align: center;">Please Log In to Your Account</h1>
        <hr>
        <form action="/login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary" style="width: 100%;background-color: grey; border-color: grey;">Log In</button>
        </form>
        <hr>
        <div style="text-align: center;">
            <h2>Don't have an account?</h2>
            <a href="/registration" class="btn btn-secondary">Register Now</a>
        </div>
    </div>

</@t.template>