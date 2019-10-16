<#import "../templates/public/index_blank.ftl" as b>
<@b.page>
<style>
  body {
    margin: 1px;
    font-family: monospace;
  }
</style>

------------------------------------------------------------------- <br/>
${resumo.dataPregao?datetime?string("dd/MM - EEEEE")} <br />
------------------------------------------------------------------- <br/><table>
  <tr>
    <td><b>FE:</b> </td>
    <td>${resumo.fechamento?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}</td>
  </tr>
  <tr>
    <td><b>AA</b>: </td>
    <td>${resumo.ajusteAnterior?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}</td>
  </tr>
  <tr>
    <td><b>AJ:</b> </td>
    <td>${resumo.ajuste?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}</td>
  </tr>
  <tr>
    <td colspan="2">---</td>
  </tr>
  <tr>
    <td><b>+1:</b> </td>
    <td>${resumo.variacao1Mais?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}</td>
  </tr>
  <tr>
    <td><b>-1:</b> </td>
    <td>${resumo.variacao1Menos?string("#,###.00")?replace(",","x")?replace(".", ",")?replace("x", ".")}</td>
  </tr>
</table>
------------------------------------------------------------------- <br/>
PANORAMA <b>(${resumo.panorama.display})</b> <br/>
&nbsp <br/>

Indices Mundiais: (${resumo.indicesMundiais.display})<br/>
DX: (${resumo.dx.display})<br/>
Estrangeiros: (${resumo.estrangeiros.display})
  Posição: ${resumo.estrangeirosOperacoes} / ${resumo.estrangeirosPosicao} <br/>
S&P: (${resumo.es.display})<br/>
CL: (${resumo.cl.display})<br/>
Noticias: (${resumo.noticias.display})<br/>

------------------------------------------------------------------- <br/>
OPERACOES <br/>
<br/>
<br/>
<br/>
------------------------------------------------------------------- <br/>
ANOTACOES <br/>
<br/>
<br/>
<br/>
------------------------------------------------------------------- <br/>
</@b.page>
