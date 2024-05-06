import logo from './novelo_logo.PNG';
import './App.css';
import './Views/Views.css';
import React, { useState } from 'react';
import * as Views from './Views/Views.js';
import {TrenutniKorisnik} from './Security/Authorisation.js';
import * as Info from './Infos/Info.js';


function App() {
  const [odabranView, setOdabraniView] = useState(<div>Dobrodošli frame</div>);

  function odaberiView(komponenta) {
    console.log(komponenta.type.name);// koristiti type.name za određivanje koji info da se otvori, jer će postojati info za svaki page u menu-u
    if (TrenutniKorisnik.isLogged()) {
      setOdabraniView(komponenta);
    } else {
      setOdabraniView(Info.NeedLoginInfo);
    }
    
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Novelo
        </p>
        <FontAwesome/>
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin="" />
        <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
      </header>
      <Menu odaberiView={odaberiView}/>
      <Account_card/>
      <div className="ViewsFrame">{odabranView}</div>
     
<img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOgAAADlCAYAAABZN7umAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABgASURBVHhe7Z17V9vIssXv9/8ih2SAPCZDDklIJic3WYd1Vg42T0t+vw3GxDy9TPDdV21EQogAuaXuqpbrt1b9NRMjS97q7t1V1f8DQRDYIgIVBMaIQAWBMSJQQWCMCFQQGCMCFQTGiEAFgTEiUEFgjAhUEBgjAhUExohABYExIlBBYIwIVBAYoyHQY/TaH1BsSCSPHI7CuzoftNCMvA8Zj/1W+P1nR0Og+6iV/oGNgkTyWEMvvKvzQQF+5H3IeNQL4fefHREoaYhA5yJEoK6GCHQuQgTqaohA5yJEoK6GCHQuQgTqaohA5yJEoK6GCHQuQgTqaohA5yJEoK6GCHQuwq5ARxgOdtHp51FrfkCx+ic2i38gF3Vh8xje0+B+LGOzvPojk6TSVfcrKuo4C+/qXDEZ4WI0xPlNnDXQU/ej+yW4X++wV1pC3ou4tw5EzlvCduUdiq2vwfOt4Fvw/S4ur8IvPjsaAr2PK4xHHRz0v6KiROstRH6BbMUC8sUVFDt59Ib9RA9CuEvwezpvoNP5gL3ik4h7zyNy/p8oNNfROuoYef4pCvQuwQ0+raDRXMGWo2/De8N7Ab/jYSiCtIT6Le2gWOIh1FzwUi53g9FxfBlenzkMCvQWwZRm2P+EXd/1UfUJths7OBZd0jA5wX7zGdFyagGblU/onI4wCS/HBnYE+oNLHPc/YNvJEXURfv/E6sMRojhDr74Y8XzMRa70wbowb7As0JCrNhrVJYeMpQXsdLoiTi5MmqgUo55T2rGIvU4VF4QPnkagUy5x1H2NfOSNYRbFL3NWt8mf8cEbsy947xWqQ3qPnVCg14yGn7HDfMq72xV5smNSQsnU78Z/g/Y5D6OBXKCKyek6Y5G+QO00vFCBEVfBWtSA6RiMnPVTPi4gC4Eq+IqUMtvnEqPRECdDHx21v6wSH2or2FaJECpm3GvO+eG/C5Moyp18KpvpVJx0XkZ+T/14gSojcSrYCFQxOlzjtyYt/RvD8PpM8n08xLejXbQ6/4JXeY5Nn2DPz/sDm6UVeGrjfdDAyYjGuYxN8HuJ/B5awdMIZCVQNW0ZBm9FVu5uaR3H4dWlR5h1tb+OMvesq0C0KnWt0vUxOGcm2KOPqb3Qc5UNlmmXzASqOEO39jTyJpKE9x774ZUl4uo8GCHdT4PMec+w11hHZzjAmFqtwbJoO+IaZw5vBc2L8DOZwVCgAeMCfD/iRpJEgjXo1TcM+uvwyxktJvCeYqvyCY2jHo1YB+9Tua/bbb573DwFGsBnPTqji6vSGg/XUawsZ1OU90UwFd6praNnM+MmjTWo/xF9ruoMYCtQPlPdBfiD8JIe4PtFA60sFgZoxDSZfL+BM8OG6PhgNfLvx48FFA54F/wxFmjAxQZ2Gfzg1RQomkucDfMolp7O12gZN7xl7DV3jFX9DNvPo/9u3Ch+xoDx6KngLdCA486L6JtrM6pbGIfXM2U6jf2CAuM6RVYRrFW3G3kcjdIU6lUww4r4W7GD/+ipYC9QTCooURtGXrBOmV7MTTWO62VzVKHK9dISahfVJK13mK89b+Av0ICz3grxFHIZfisYMZ2vZ2US3hJ2g6lvorraUbD8ifrsmOFKfrUTArVXXiRhNbwXKPYP8D18zLMwOVzTf2l7b9Ax3wwhFdwQaMDoYFWMmIzGtCD6bDbFDFrLkZ8VJ/KNEu8Uxls4I1CgjaqMohmOWdrJJPktPEM1/dxNYzgkUBlF5yL816h9e6S1TJIUv/JXp1qdOiVQWYvOSyxgq37/aHrWfRXxb+KE2loZhZ/iBm4JNED/4Ui4Fjn/LVq/rU2P0ChH//+Phvc3DlxZfIY4J1CjrS4k+IW3hEK3/dPpvfiqvb2y2WqGH+IO7gk04Kj9LPIBSGQ1FrBZ3cAwGP30M8vcModucFKguNxGQUbRuYtccRUF3awyx8yhG9wUKK5w0GBU1G0tniB/+2Am1Zrk1mFM6nyYHwcS/ehldPPfw55GjVXsaPQzcjvcM4ducFSgAcdfsBX5MLIROf859mrXJ6MdnJhq6qWakqkDr9RJdepUsawWlrtnDt3grkCTuHkMY3pKljqyztApWbFRRwOeVNCaniqWjTK6rXY7/HLu4bBALXQXNxrBdLX8DrV9O6dk6TK5HDjetuUVGkz7DcXBaYG6t+WygHxpFZX9Bk4d7EM7uezhoPuJ9XmddyNX2/61ltcx3BZogBNbLtPOAnkMLhwpoXgUdV6nh2qVe9+lpygdufcivI3zAk2ycW06ct5L+N2y8d48lExGLbQaL3keWZ+BQ6/cFyhG6NR4bRnk/L9Q7hO1oiRCTX87zb+wyUioWTj0KgMCDX4cR3/zaNE5LUCeL2HeZTKqolF7QT/1dago+yEyIVCgTJxZtIQC8UGv3Ph+toNiidBMKv5Xq1MDNzIg0OvzXCIfkqXI1XxnKvTtctNkLfq+mY1F+IcuJvf9ivMCZdGBPiPTKVOoaW+tumR/2uu9RI3ZcYKz4rRAOZ0p6mqupz3UaPrefud9fw09hzdC3RUoqwOWgnC0WsI2k/Nd+EW7rnuuvD4tVXMRNwU66aJW5laN4Wa9IQmTAbr1Z1anvPl6AS7OcRwU6BX6zcXIh0AdLrVzpOcSR93XFv0DdYJ2Kie9WsU5gbI8Jv8mHC5romI0/GzPR3DQNHJLoExOO7s/3C0MpmRyuoE9W36CI2ey3OCQQPdRZ7fujAgxi7SYjHbhWzr7Jl/3nalwcUSg18kIbtQjilmkzXhTvyH1TOFOEoMTAuW03xknXK7gJ8VmGxtH9kcdEKgjU9vbIWaRBvYbwbkw1WUvUPqzQXVCzKKZIWmlusi+oJu3QMdb7va/FbNoJs6DF3HkfTQdxc8YMJ7tMBboCL26y71v3W5WZRfaDo3bjBMY2Ap0MvzsfN9bMYtiQt3j2FtFm+lilKlA91ErRdxI10LMohjwOCUg36ywTNNkKdDsHNQrZtGjsDln5wVqp+E1MYKfQDN2SK/rfVlNw+m8V47Pip1As3fMvZhF99NGldXLmF8WGC+BTioocSrCTinELIpGvxujucQVbqMoK4EaTUrw/wmPyngSsygCfXMoX/tfeMZe5LxGUUYCNTndWYQ/GBEetuT+EQSpo20OXQvI5FKI0yjKRqBjg4XY+drudbsLwsOWxCz6FW1z6EeG1hGaFVNTXT6+AROBGswkubMJTXfYkphFP9GdLd3Ztjr9j7Eqp61Wk8W+KA+BGswk+S2Ni/CwJTGLrtE2h37rP3yFQcvQC5dJr2MGAjWYSRLZ3oLwsKUMnLaVHP37H9mU7bIA39AoyuHwJXqBGsskUdOh6HoSusOWxCzSn8Hc764ac/8ZVCSRC3R8sBp9c5JGaR33u+V0G+TzbhYdd15E3pdH4yGxGDP/6LdciAVqyhx6Cv/w4RxYuhSzFTTnNj03JXMoAlPPk7rXMa1AT9fNNIl6cPQMIUzSzsLBsjrom0MxEj1MjaLESSakAjWz5RG3goSwzGkuzSJ9cyiu+23m90TrGxAK1NA6cJY3Hlmh8ByaRdrm0Az7x4ZmRbl6gWyaSydQQ9Pb2dpX0LXamDezSHd0m+0+GZoVEU5zyQSq7eY9GLMbMGT5ufN06K/2+lBjpmFkVqSuI/x8yxAJ9BjNStSNSBZaoxJhfu68mEXaL0GttbqZdjmqJQoFNAK93EIh4iYkC/11HVl+7lyYRfrmkO4LzMiWi2rPGX6+TUgEOjlcS39amWSdQJafSzd1sob2vV1FW3cJMNow8DxpehaRCLTfTH8hn2xDmS4/N+uH/toxh+5iZglF0QCOQKBdVFNfIyTftiDLz82yWWTTHLqDiU71FNst9gVqYvqRig1Ol5+bVbPIrjl0BxO/M4J1qH2BDt6nvv5M681Glp+bSbNIf485nReWCTfXfh61dYEO288jvniySG1tQJafy6/dY2J09yNTnPIPWsvRfyNBeH27GWCWBTpCuxr9xfUjzbcaXX5u1swiXXGkeh+OPqbuK2y3u+GH28GyQA10jU97XUCVn5sls0jbHEp5JmFiv726ZTVF065ADSzc03fW6PJzd3vZmOdqm0OpdzDoo572s7RsFNkVqIEph4kftbEuD49FJg791X3BxS0TnI3099wTJFBoYFWg6f/wl1EZhh+eJmIW6aNtDpmpGHHmN3cPVgWavoNr6m0mZpEe+vfNWEvS4WdsRvy9JGHTybUq0F49+gtrR+nfMPYyIzOL1tBzVaHaMw+DTb0nu/Ai/6Z+2Dwy36JADWwc13YNjjZUZpGZtZgNtBM9Khs4Dz8jfdJPLc3V/fCzzWNRoJXU6y43W83ws81AVsztpFnEyxz6iYG9d4tbLRYFWoAf9WUTxE6nH362IciKuR00i5iZQ7fZr6dcqRSna2RK2BOogT1Q/zD8bINQFXNTVfDrwdAcukXq7XW8jzA8NPzAnkBTbxL2HFUbBbRUxdyEjapmhqM5dItJ/23E304Sa+iFn20aewJN3e62VeFOVcztjlmkaw5Z62x4uBb59/XjLbqWXp72BJr6TbKX0fF/JzmUG2so2o6OH7weuHOIbivi2h+ND2ifWPqVOzs4OC1Qe9MMwXFSX16JQGOECFSIiQg0BiJQgQoRaAzSFqhFq1twnNS3+ESgj4cIVIiLCDQGMsUVqJApbgxEoAIVItAYiEAFKkSgMUhdoHZbTwgOI4kKMXD4JgmOk/rgkMVUv9SnGZaS5QXnkWT5ODhabia4j5SbxcLBgm0hE0jBdizca3kiZAFpeRIT15qGCdnAwHm09UL42eaxKFDH2m7Ggqp/rp1OBLfRbaBmrSj7PqTtZnzcaVw9A0T9c23+SPSniclPyk6MNK6Oj+tt+KMh6p9r89Bf3b5MDA4mlqMfZsGRw5NmhaZ/rr3RSbezod1RPho5PGkWjBw/aK/L970Q9c+1sr7T/m7218m/I8cPzogDB/hqQtM/1/zb3FlzSCEH+M4K9yPwE0DUP3e3a3KVp7u+ZmAOKeQI/NlJ38n9B5P+sUT9c00aMboONQNzSDFoLUdfX4Kw6eAqrAsUg/epGyrpH4Ovx+RwLUNmkf4er9lRPS4GEmMIZmv2BWrAKGJzTAKVWWTiBaX7Xbw36HCo0zXxOyPwO+wL1ETqFZc1TwCJWWRAFLrmEJcTws97K5HXlyQoZmoEAjWxN8XoNLBMmEW65hCXYxOP0axEXV+yoPA6SARqZK3Gpg1nBswiXXOIy8HDJqa3RB08SARqZH+K0TR3cvR36vb+45FWCpquOcTnNDbto/gfCqL9dhqBGpqCsNgcn9JGNe2EjBiRyvpP96xPNueZmnBv6ZZQRAINJJp2G4ppMElaCDDyFn8sUjCLdK/bxknZsTBSXaRmZ+HnW4ZMoOk3EbsODgnaU7RPnU4Wycwi3dGHizlkqD6XcHZAJ1BT00A2Uy2iYu4kRo3r5pChlyJlIgyhQE3tGfIxK2iKuXVHM/fNITPLJlrzkVSgpqa5NruuPQxNMbeWWeS6OWQqi4v4+9EK1NgP+Cn8Qx5vdZJibg2zyHVzyJQpR50ZRSxQEy0pwuAyihLl58427dT1AzgUZQcYu8f05he5QM25nWptxMK6oMnPncG40U2s4LLvfNZbMTNLYWB+0QvUpNvpf0Sfw/qIJD837ttfNzWRSebWZQG+oRkKh7I5BgINMOh28tgXpcnPjbV+0p3BsCjKvsKgZWh2wqRsjodATbqd3iraDOZhJPm5MRxIXXOFRVH26X+wY2j03Go1Sc2hG5gIFBgfrhn7Aedru8EYRg1Ffu5je5Sa18RidDlCs2JqVsLE/ApgI1CzP+BF+AN6iZLk5z5gdOiO6hyKskcHq8a2r/gUXbASqEE3ToW/hh71XSfJz73PLEpiDoUfQcW4AN+PurY04hkqQx5liwpWAsWkgpKxGx+8+esF4qkuTX5u5DGNzh7ncIZuzdw95DR6KngJNMDoKBq8/b0+8c4WRX5uhFmkuzdLbQ6N+m8Nmm1cqnJ+wk6gmBjoPn87vJeoE7Su+AlFfu5ds0jzHlObQwZdWxXcRk8FP4EGmDQAVOTK6xgSuhwk+bm3zCInzaFJF7Wyyb1kmp5Dj8FSoKbaVtwO0vUoSX7uzdaBrjlEOf07Q6++GHFN6YVqacJh3/MuTAWq3vLpn6vxayxgp9MleygU+bnTyhNdc4gsL/UKw85LszMOJsksUbAVqHrT9+qmHc9F+IdEphFFfq73N2paqXF0RdkjgwksN8GmTU4EjAUaMN4yv2/ovQzWHhT7XkT9c3WCqGh5crpu1BSahmqnyXFuG8JboAFmt13CIBIpTf/c2YOkKNtoMsJNLLLppXwf7AWqDKO6UfcuDP8N2ue2HxZFfu6sQZCXakWcyij02W2r3MUBgVqa6qggEClJfu4MYX1vcFxCqWjjhcwg9TMGTgjUipN3E/5rNG1Od0nyc+OG5aJsW+KkNAdnxBGBKixNdVV4r1A7sZUyQ9Q/N05YNIcmo91gWmvn+bowtb3BIYEGXGxg19Zo4y3B75/Y2Sc1vuerF7bMocnpBvYsrDmnwaUNTkzcEmiAjX2xn7GIvW4b38O/bQ6OZpEdc2g0/GzHX1BBtqWmj3MCVVPCftNs2tevsYDN2hZODb91uZlF5s2hSxx1X1t82arMMb4JCffhoEADjCdO/x654nt0TTq8rMwiw+bQZIBu/ZnVggH6WmA93BSowtJe2S/hPQvWpQND61JGZpHBouzJ+S58K07tz6CuXkqCuwINsLY/+kuoKW8exyYGGJLDln4PM0XZlzjuv8eW7eflyH7nfTgtUIVd0+hWeC9QHqTt8tIctvRLGCjKnoyqqFWX7NfAOmgK3cV5gVpNYvgtgtG0uo7+KL0fAUkx961ItyhbjZofsE2ytnYnGeEhMiBQxRkOmnZNh1/CW0KhU8VFGr9sosOWriO9ouzvZzsolp5E/A0bQVvrmyYZEajCfNX9oxFMe4v9HsYJfxkkhy2pSKEoW01nG7UXhLOAQJytupOObRQZEmjAZIB2jVikQeT8v1BOIlSSw5aSFWVPLnvoNP/CJvFWkavbKfeRLYEqmIhURc57Cb9bxtnMS1SCYm5vDT2NF8pk1EKr8RJ5YmGqyNd301lmMCJ7AlUwEuk0vGXsNfMYXMS3R20Xc89mDl1hfOqhWl0mNbRuR9ZGzhuyKVDF5ARd6jXpb7GAfGkVlf0GTi8fG1Zt5ufGM4fUNPag+wl7RSrzJzqyKk5FdgU6hdjdfTCeIF9+h1q/gZNx9MhqLT/3oQOWLgcY9Nfhl/9geB8XsNUsZVaciowLVHGJo84rpiL9GTn/TxRaX9Eb9nFxM7payc+9Yw5NRrg4qaDV+RCMlE8Z37cn2OnYqDSiZQ4EqrjC6cFbcodxlsj5z7FXe4+C6b1EL5hyH+RRa77DXonjKBkVi8FLxVRONC/mRKDXfD/5aq8wWMJMeK9QHbqfIRSXuRLolHEdFculahLpRK74EfspplW6wPwJVEFQjyiRJFTO84azJWNJmE+BTgnWpRTlTxKzhcpzttJ2hidzLNBrJiMfZbKkbomHIue/ReuM8kBSeuZeoFMmJ+h3/umUy5vtWMBWfcdMUbxjiEBvQVsiJTEN/zVq3yy1O3UAEehvnOFQRlOCeILthoyadxGB3gNZm445jFzpAzpzvta8DxHoI8i012BMC9wP5tahjYMINBaXOD78goKls0MyH94SdpsynY2DCHQmbppgiVD1Qq0z8zias2ygJIhAdZiMMFQjKrO6SLbhPRVhaiICTcQlzob5YI3KuSyLMKadJHYwfLQ4XbgPEWhKfL9ooNVckdTBIHLFFZT3Gxq9mIS7iEDTZjr9XUexwqdfj5Xw/sBObR2905EkGaSICNQkV98YtwtJI55gq/IJjaPkvYCFaESgtrg6x7ejr6hU/2TRolI71EhZ/RKIsgPxfMwjAiXhCuPzBnr76ygHgt3kvG0TCHK78g6Vro/B2bkkFVhGBMqE7+NhMMLuotX5F7zKc2z6BFs4gRg3SyvwmutoDRo4Gcl6khoRKGdUh73RECdDH51+MD1ufECxtoLt4jI2Vcw48ub88N8pEQafVe7kg8+t4FvwN350EhRYIQLNIJOJiC0riEAFgTEiUEFgjAhUEBgjAhUExohABYExIlBBYIwIVBAYIwIVBMaIQAWBMSJQQWCMCFQQGCMCFQTGiEAFgTEiUEFgC/D/H/8pLLvBBFQAAAAASUVORK5CYII=" />


</div>
  
  );
}

function FontAwesome() {
  return (
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"  integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  )
}

function Menu({odaberiView}) {
  return (
    <div class="menu_wrapper">
  <input type="checkbox" id="toogle" class="hidden-trigger"></input>
  <label for="toogle" class="circle">
 <svg class="svg" xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" width="48" height="48" xmlSpace="preserve" version="1.1" viewBox="0 0 48 48">
<image width="48" height="48" xlinkHref="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAQAAAD9CzEMAAAAbElEQVR4Ae3XwQnFQAiE4eVVsGAP1mkPFjwvQvYSWCQYCYGZv4Dv5MGB5ghcIiDQI+kCftCzNsAR8y5gYu2rwCBAgMBTgEC3rek2yQEtAZoDjso8AyaKexmIDJUZD40AAQIE0gwx449GgMC9/t0b7GTsa7J+AAAAAElFTkSuQmCC"></image>

     </svg>
  </label>
  
  <div class="subs">
    <button class="sub-circle" onClick={() => odaberiView(<Views.InputView/>)}>
      <input value="1" name="sub-circle" type="radio" id="sub1" class="hidden-sub-trigger"></input>
      <label for="sub1">Inputi</label>
    </button>
    <button class="sub-circle" onClick={() => odaberiView(<Views.AnalizaView/>)}>
      <input value="1" name="sub-circle" type="radio" id="sub2" class="hidden-sub-trigger"></input>
      <label for="sub2">Analiza</label>
    </button>
    <button class="sub-circle" onClick={() => odaberiView(3)}>
      <input value="1" name="sub-circle" type="radio" id="sub3" class="hidden-sub-trigger"></input>
      <label for="sub3">Kalkulator</label>
    </button>
    <button class="sub-circle" onClick={() => odaberiView(4)}>
      <input value="1" name="sub-circle" type="radio" id="sub4" class="hidden-sub-trigger"></input>
      <label for="sub4">Favoriti</label>
    </button>
    <button class="sub-circle" onClick={() => odaberiView(Test)}>
      <input value="1" name="sub-circle" type="radio" id="sub5" class="hidden-sub-trigger"></input>
      <label for="sub5">Premije</label>
    </button>
   </div>
</div>
  )

}

function Test() {
  return (
    <div class="notification is-link" id="fs" style={{width: 500}}>
  <button class="delete"></button>
  Primar lorem ipsum dolor sit amet, consectetur
  adipiscing elit lorem ipsum dolor. <strong>Pellentesque risus mi</strong>, tempus quis placerat ut, porta nec nulla. Vestibulum rhoncus ac ex sit amet fringilla. Nullam gravida purus diam, et dictum <a>felis venenatis</a> efficitur.
</div>
  )
}

function Account_card() {
  return (
    <div class="account_card" id='acc_card'>
    <div class="card_image"></div>
    <div class="card_title">{TrenutniKorisnik.getName()}</div>
    <div class="card_descripion"><button onClick={() => TrenutniKorisnik.setName("tpanonius@gmail.com")}>Login</button></div>

</div>)
}
export default App;
