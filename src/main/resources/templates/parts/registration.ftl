<#macro registration path>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Login:</label>
        <div class="col-sm-6">
            <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="Login"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password2"
                   class="form-control ${(password2Error??)?string('is-invalid', '')}"
                   placeholder="Retype Password"/>
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email:</label>
        <div class="col-sm-6">
            <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                   class="form-control ${(emailError??)?string('is-invalid', '')}"
                   placeholder="some@some.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Name:</label>
        <div class="col-sm-6">
            <input type="text" name="name" value="<#if user??>${user.name}</#if>"
                   class="form-control ${(nameError??)?string('is-invalid', '')}"
                   placeholder="Name"/>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">SurName:</label>
        <div class="col-sm-6">
            <input type="text" name="surName" value="<#if user??>${user.surName}</#if>"
                   class="form-control ${(surNameError??)?string('is-invalid', '')}"
                   placeholder="SurName"/>
            <#if surNameError??>
                <div class="invalid-feedback">
                    ${surNameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Address:</label>
        <div class="col-sm-6">
            <input type="text" name="address" value="<#if user??>${user.address}</#if>"
                   class="form-control ${(addressError??)?string('is-invalid', '')}"
                   placeholder="Address"/>
            <#if addressError??>
                <div class="invalid-feedback">
                    ${addressError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Age:</label>
        <div class="col-sm-6">
            <input type="text" name="age" value="<#if user??>${user.age}</#if>"
                   class="form-control ${(ageError??)?string('is-invalid', '')}"
                   placeholder="Age"/>
            <#if ageError??>
                <div class="invalid-feedback">
                    ${ageError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Mobile:</label>
        <div class="col-sm-6">
            <input type="text" name="mobile" value="<#if user??>${user.mobile}</#if>"
                   class="form-control ${(mobileError??)?string('is-invalid', '')}"
                   placeholder="Mobile"/>
            <#if mobileError??>
                <div class="invalid-feedback">
                    ${mobileError}
                </div>
            </#if>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Registration</button>
</form>
</#macro>