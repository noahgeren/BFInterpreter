// Minified
bf=(c,i)=>{for(l=3e4,m=Array(l).fill(0),b=256,p=0,ls=[],o='',cl=c.length,ip=0;ip<cl;ip++)(fn={'>':_=>{p=(p+1)%l},'<':_=>{p=(p-1+l)%l},'+':_=>{m[p]=(m[p]+1)%b},'-':_=>{m[p]=(m[p]-1+b)%b},'.':_=>{o+=String.fromCharCode(m[p])},',':_=>{m[p]=i.charCodeAt(0)||0;i=i.substr(1)},'[':_=>{m[p]&&ls.push(ip)||(_=>{for(lc=0;ip<cl&&('['===c[ip]&&lc++||']'!==c[ip]||--lc);ip++);})()},']':_=>{Number.isNaN(ip=ls.pop()-1)&&(o='Error')&&(ip=cl)}}[c[ip]])&&fn();return o}

// Prettier
/**
 * Interprets Brainfuck code
 * Args:
 *  c   String  The Brainfuck code to interpret
 *  i   String  The input (optional)
 * Data Dictionary:
 *  l   Length of memory array
 *  m   Memory array
 *  b   Bounds for memory cells (default is byte)
 *  p   Pointer to cell in memory array
 *  ls  Loop stack
 *  o   Output string
 *  cl  Code length
 *  ip  Instruction pointer
 *  fn  Function for current operator
 *  lc  Loop counter
 */
bf = (c, i) => {
	for (l = 3e4, m = Array(l).fill(0), b = 256, p = 0, ls = [], o = '', cl = c.length, ip = 0; ip < cl; ip++)(fn = {
		'>': _ => {
			p = (p + 1) % l
		},
		'<': _ => {
			p = (p - 1 + l) % l
		},
		'+': _ => {
			m[p] = (m[p] + 1) % b
		},
		'-': _ => {
			m[p] = (m[p] - 1 + b) % b
		},
		'.': _ => {
			o += String.fromCharCode(m[p])
		},
		',': _ => {
			m[p] = i.charCodeAt(0) || 0;
			i = i.substr(1)
		},
		'[': _ => {
			m[p] && ls.push(ip) || (_ => {
				for (lc = 0; ip < cl && ('[' === c[ip] && lc++ || ']' !== c[ip] || --lc); ip++);
			})()
		},
		']': _ => {
			Number.isNaN(ip = ls.pop() - 1) && (o = 'Error') && (ip = cl)
		}
	} [c[ip]]) && fn();
	return o
}