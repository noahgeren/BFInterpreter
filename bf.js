// Minified
bf=(c, i)=>{l=30000,m=Array(l).fill(0),b=256,p=0,ls=[],o="",cl=c.length;for(ip=0;ip<cl;ip++)(fn={'>':_=>{p=(p+1)%l},'<':_=>{p=(p-1+l)%l},'+':_=>{m[p]=(m[p]+1)%b},'-':_=>{m[p]=(m[p]-1+b)%b},'.':_=>{o+=String.fromCharCode(m[p])},',':_=>{m[p]=(i.charCodeAt(0)||0);i=i.substr(1)},'[':_=>{m[p]&&ls.push(ip)||(_=>{for(lc=0;ip<cl;ip++)if(!(c[ip]==='['&&lc++)&&c[ip]===']'&&!--lc)break})()},']':_=>{Number.isNaN(ip=ls.pop()-1)&&(o="Error")&&(ip=cl)}}[c[ip]])&&fn();return o}

// Prettier
// Accepts strings for code and input
let bf = (c, i) => {
    let l = 30000, m = Array(l).fill(0), b = 256, p = 0, ls = [], o = "", cl = c.length, fn;
    for(let ip = 0; ip < cl; ip++) {
        (fn = {
            '>' : _ => { p = (p + 1) % l },
            '<' : _ => { p = (p - 1 + l) % l },
            '+' : _ => { m[p] = (m[p] + 1) % b },
            '-' : _ => { m[p] = (m[p] - 1 + b) % b },
            '.' : _ => { o += String.fromCharCode(m[p]) },
            ',' : _ => { m[p] = (i.charCodeAt(0) || 0); i = i.substr(1) },
            '[' : _ => { m[p] && ls.push(ip) || ( _ => {
                for(let lc=0; ip < cl; ip++)
                    if(!(c[ip] === '[' && lc++) && c[ip]=== ']' && !--lc)
                        break
                })() },
            ']' : _ => { Number.isNaN(ip = ls.pop() - 1) && (o = "Error") && (ip = cl)}
        }[c[ip]]) && fn();
    }
    return o;
}