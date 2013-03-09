<%@ taglib uri="http://leve.org/tags" prefix="l"%>
<l:page pageType="login" >
<l:header />



<div class="container">
  <form method="POST" action="j_security_check" class="form-signin">
    <div class="control-group">
    	<l:label label="user" foratt="j_username"/>
      	<input type="text" id="j_username" name="j_username" required>
    </div>
    <div class="control-group">
      <l:label label="password" foratt="j_password"/>
      <input type="password" id="j_password" name="j_password" required>
    </div>
    <input type="submit"  class="btn btn-primary" value="Login" />
    <input type="reset"  class="btn" />
  </form>
</div>

</l:page>