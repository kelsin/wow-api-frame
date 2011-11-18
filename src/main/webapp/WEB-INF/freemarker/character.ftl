<#include "/header.ftl">
<div class="character">
  <h1>${character.name} of ${character.guild.name}</h1>
  <h2>${realm.name}</h2>
  <dl>
    <dt>Talent Specs</dt>
    <#list character.talents as talent>
      <dd>${talent.name}</dd>
    </#list>
  </dl>
  <h3>Raids</h3>
  <ul>
    <#list character.progression.raids as raid>
      <li>
        <h4>${raid.name}</h4>
        <h5>Bosses</h5>
        <ul>
          <#list raid.bosses as boss>
            <li>${boss.name}</li>
          </#list>
        </ul>
      </li>
    </#list>
  </ul>
</div>
<#include "/footer.ftl">
