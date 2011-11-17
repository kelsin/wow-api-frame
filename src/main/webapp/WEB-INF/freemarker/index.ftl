<#include "/header.ftl">
<form method="post" action="/submit">
  <label for="character">Character</label>
  <input type="text" name="character" />
  <label for="realm">Realm</label>
  <select name="realm">
    <#list realms as realm>
      <option value="${realm.slug}">${realm.name}</option>
    </#list>
  </select>
  <input type="submit" name="submit" value="Load Character" />
</form>
<#include "/footer.ftl">
