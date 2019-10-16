<#import "../templates/public/index_blank.ftl" as b>
<@b.page>
<style>
  body {
    margin: 1px;
  }

  table, th, td {
    border: 1px solid #b2b2b2;
    border-collapse: collapse;
    font-family: monospace;
    text-align: center
  }

  .marcador_preco {
    font-size: xx-small;
    width: 20px;
    font-weight: bold;
  }

  .marcador_ultimo {
    background-color: #3333cc;
    color: white;
    font-weight: bold;
  }

  .marcador_1perc_mais {
    background-color: #33cc33;
    font-weight: bold;
  }
  .marcador_1perc_menos {
    background-color: #ff0000;
    font-weight: bold;
    color: white;
  }

  .marcador_pontos_geral {
    background-color: #cfe0e8;
    font-weight: bold;
  }

  .marcador_variacao_1less {
    background-color: #d64161;
    color: white;
    font-weight: bold;
  }
  .marcador_variacao_1plus {
    background-color: #82b74b;
    color: white;
    font-weight: bold;
  }
</style>

<table border="1">
  <tr>
    <td class="marcador_preco"></td>
    <td style="width:70px"><b>PREÇO</b></td>
    <td style="width:40px"><b>#</b></td>
    <td style="width:100px; text-align: left;"><b>Evento</b></td>
  </tr>

  <#if precos?? && (precos?size > 0) >
    <#list precos as preco>
    <tr>
      <td class="marcador_preco">
        <#if (preco.preco / 10)?string(".00")[4..] == "00" > >> </#if>
      </td>
      <#if preco.dataPoints?? && (preco.dataPoints?size > 0) >
        <#if (preco.dataPoints[0].tipo.code == "+1%") >
          <td class="marcador_variacao_1plus">
        <#elseif (preco.dataPoints[0].tipo.code == "-1%") >
          <td class="marcador_variacao_1less">
        <#elseif (preco.dataPoints[0].pregao == 0) >
            <td class="marcador_ultimo">
        <#else >
            <td class="marcador_pontos_geral">
        </#if>
      <#else >
        <td>
      </#if >
        ${preco.preco?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}
      </td>
      <td>
        <#if preco.dataPoints?? && (preco.dataPoints?size > 0) >
        <#list 1..preco.dataPoints?size as item >*</#list >
        </#if >
      </td>
      <td style="text-align: left;" >
      <#if preco.dataPoints?? && (preco.dataPoints?size > 0) >
        <#list preco.dataPoints as dataPoint >${dataPoint.tipo.code}(${dataPoint.pregao}) </#list >
      </#if >
      </td>
    </tr>
    </#list>
  </#if>

</table>

</@b.page>
