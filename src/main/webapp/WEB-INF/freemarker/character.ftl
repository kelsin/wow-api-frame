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
  <table>
    <thead>
      <tr>
        <th>Instance</th>
        <th>Normal Runs</th>
        <th>Heroic Runs</th>
        <th>Boss</th>
        <th>Normal Kills</th>
        <th>Heroic Kills</th>
      </tr>
    </thead>
    <tbody>
      <#list character.progression.raids as raid>
        <#list raid.bosses as boss>
          <tr class="<#if raid_index % 2 == 0>odd<#else>even</#if>">
            <td><#if boss_index == 0><a href="http://us.battle.net/wow/en/zone/${raid.slug}/" title="${raid.name}" alt="${raid.name}">${raid.name}</a></#if></td>
            <td><#if boss_index == 0>${raid.normal}</#if></td>
            <td><#if boss_index == 0>${raid.heroic}</#if></td>
            <td><a href="http://us.battle.net/wow/en/zone/${raid.slug}/${boss.slug}" title="${boss.name}" alt="${boss.name}">${boss.name}</a></td>
            <td>${boss.normalKills}</td>
            <td>${boss.heroicKills}</td>
          </tr>
        </#list>
      </#list>
    </tbody>
  </table>
</div>
<#include "/footer.ftl">
