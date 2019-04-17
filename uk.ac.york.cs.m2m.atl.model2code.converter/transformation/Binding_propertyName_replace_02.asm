<?xml version = '1.0' encoding = 'ISO-8859-1' ?>
<asm version="1.0" name="0">
	<cp>
		<constant value="Book2Publication"/>
		<constant value="links"/>
		<constant value="NTransientLinkSet;"/>
		<constant value="col"/>
		<constant value="J"/>
		<constant value="main"/>
		<constant value="A"/>
		<constant value="OclParametrizedType"/>
		<constant value="#native"/>
		<constant value="Collection"/>
		<constant value="J.setName(S):V"/>
		<constant value="OclSimpleType"/>
		<constant value="OclAny"/>
		<constant value="J.setElementType(J):V"/>
		<constant value="TransientLinkSet"/>
		<constant value="A.__matcher__():V"/>
		<constant value="A.__exec__():V"/>
		<constant value="self"/>
		<constant value="__resolve__"/>
		<constant value="1"/>
		<constant value="J.oclIsKindOf(J):B"/>
		<constant value="18"/>
		<constant value="NTransientLinkSet;.getLinkBySourceElement(S):QNTransientLink;"/>
		<constant value="J.oclIsUndefined():B"/>
		<constant value="15"/>
		<constant value="NTransientLink;.getTargetFromSource(J):J"/>
		<constant value="17"/>
		<constant value="30"/>
		<constant value="Sequence"/>
		<constant value="2"/>
		<constant value="A.__resolve__(J):J"/>
		<constant value="QJ.including(J):QJ"/>
		<constant value="QJ.flatten():QJ"/>
		<constant value="e"/>
		<constant value="value"/>
		<constant value="resolveTemp"/>
		<constant value="S"/>
		<constant value="NTransientLink;.getNamedTargetFromSource(JS):J"/>
		<constant value="name"/>
		<constant value="__matcher__"/>
		<constant value="A.__matchBook2Publication():V"/>
		<constant value="__exec__"/>
		<constant value="NTransientLinkSet;.getLinksByRule(S):QNTransientLink;"/>
		<constant value="A.__applyBook2Publication(NTransientLink;):V"/>
		<constant value="getAuthors"/>
		<constant value="MBook!Book;"/>
		<constant value=""/>
		<constant value="0"/>
		<constant value="chapters"/>
		<constant value="author"/>
		<constant value="CJ.including(J):CJ"/>
		<constant value="J.asSet():J"/>
		<constant value="J.=(J):J"/>
		<constant value="25"/>
		<constant value=" and "/>
		<constant value="J.+(J):J"/>
		<constant value="26"/>
		<constant value="7:49-7:51"/>
		<constant value="5:2-5:6"/>
		<constant value="5:2-5:15"/>
		<constant value="6:3-6:4"/>
		<constant value="6:3-6:11"/>
		<constant value="5:2-7:3"/>
		<constant value="5:2-7:12"/>
		<constant value="8:3-8:6"/>
		<constant value="8:12-8:15"/>
		<constant value="8:18-8:20"/>
		<constant value="8:12-8:20"/>
		<constant value="11:4-11:11"/>
		<constant value="11:14-11:24"/>
		<constant value="11:4-11:24"/>
		<constant value="9:4-9:14"/>
		<constant value="8:9-12:8"/>
		<constant value="8:3-12:8"/>
		<constant value="5:2-13:3"/>
		<constant value="authorName"/>
		<constant value="acc"/>
		<constant value="getNbPages"/>
		<constant value="nbPages"/>
		<constant value="18:36-18:37"/>
		<constant value="16:2-16:6"/>
		<constant value="16:2-16:15"/>
		<constant value="17:3-17:4"/>
		<constant value="17:3-17:12"/>
		<constant value="16:2-18:3"/>
		<constant value="19:3-19:6"/>
		<constant value="19:9-19:14"/>
		<constant value="19:3-19:14"/>
		<constant value="16:2-20:3"/>
		<constant value="f"/>
		<constant value="pages"/>
		<constant value="__matchBook2Publication"/>
		<constant value="Book"/>
		<constant value="IN"/>
		<constant value="MMOF!Classifier;.allInstancesFrom(S):QJ"/>
		<constant value="J.getNbPages():J"/>
		<constant value="J.&gt;(J):J"/>
		<constant value="B.not():B"/>
		<constant value="33"/>
		<constant value="TransientLink"/>
		<constant value="NTransientLink;.setRule(MATL!Rule;):V"/>
		<constant value="b"/>
		<constant value="NTransientLink;.addSourceElement(SJ):V"/>
		<constant value="out"/>
		<constant value="Publication"/>
		<constant value="NTransientLink;.addTargetElement(SJ):V"/>
		<constant value="NTransientLinkSet;.addLink2(NTransientLink;B):V"/>
		<constant value="26:4-26:5"/>
		<constant value="26:4-26:18"/>
		<constant value="26:21-26:22"/>
		<constant value="26:4-26:22"/>
		<constant value="29:3-33:4"/>
		<constant value="__applyBook2Publication"/>
		<constant value="NTransientLink;"/>
		<constant value="NTransientLink;.getSourceElement(S):J"/>
		<constant value="NTransientLink;.getTargetElement(S):J"/>
		<constant value="3"/>
		<constant value="title"/>
		<constant value="J.getAuthors():J"/>
		<constant value="authors"/>
		<constant value="30:15-30:16"/>
		<constant value="30:15-30:22"/>
		<constant value="30:4-30:22"/>
		<constant value="31:15-31:16"/>
		<constant value="31:15-31:29"/>
		<constant value="31:4-31:29"/>
		<constant value="32:15-32:16"/>
		<constant value="32:15-32:29"/>
		<constant value="32:4-32:29"/>
		<constant value="link"/>
	</cp>
	<field name="1" type="2"/>
	<field name="3" type="4"/>
	<operation name="5">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<push arg="7"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="9"/>
			<pcall arg="10"/>
			<dup/>
			<push arg="11"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="12"/>
			<pcall arg="10"/>
			<pcall arg="13"/>
			<set arg="3"/>
			<getasm/>
			<push arg="14"/>
			<push arg="8"/>
			<new/>
			<set arg="1"/>
			<getasm/>
			<pcall arg="15"/>
			<getasm/>
			<pcall arg="16"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="24"/>
		</localvariabletable>
	</operation>
	<operation name="18">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
		</parameters>
		<code>
			<load arg="19"/>
			<getasm/>
			<get arg="3"/>
			<call arg="20"/>
			<if arg="21"/>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<dup/>
			<call arg="23"/>
			<if arg="24"/>
			<load arg="19"/>
			<call arg="25"/>
			<goto arg="26"/>
			<pop/>
			<load arg="19"/>
			<goto arg="27"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="19"/>
			<iterate/>
			<store arg="29"/>
			<getasm/>
			<load arg="29"/>
			<call arg="30"/>
			<call arg="31"/>
			<enditerate/>
			<call arg="32"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="33" begin="23" end="27"/>
			<lve slot="0" name="17" begin="0" end="29"/>
			<lve slot="1" name="34" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="35">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="4"/>
			<parameter name="29" type="36"/>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<load arg="19"/>
			<call arg="22"/>
			<load arg="19"/>
			<load arg="29"/>
			<call arg="37"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="6"/>
			<lve slot="1" name="34" begin="0" end="6"/>
			<lve slot="2" name="38" begin="0" end="6"/>
		</localvariabletable>
	</operation>
	<operation name="39">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<pcall arg="40"/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="0" name="17" begin="0" end="1"/>
		</localvariabletable>
	</operation>
	<operation name="41">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<getasm/>
			<get arg="1"/>
			<push arg="0"/>
			<call arg="42"/>
			<iterate/>
			<store arg="19"/>
			<getasm/>
			<load arg="19"/>
			<pcall arg="43"/>
			<enditerate/>
		</code>
		<linenumbertable>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="33" begin="5" end="8"/>
			<lve slot="0" name="17" begin="0" end="9"/>
		</localvariabletable>
	</operation>
	<operation name="44">
		<context type="45"/>
		<parameters>
		</parameters>
		<code>
			<push arg="46"/>
			<store arg="19"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="47"/>
			<get arg="48"/>
			<iterate/>
			<store arg="29"/>
			<load arg="29"/>
			<get arg="49"/>
			<call arg="50"/>
			<enditerate/>
			<call arg="51"/>
			<iterate/>
			<store arg="29"/>
			<load arg="19"/>
			<load arg="19"/>
			<push arg="46"/>
			<call arg="52"/>
			<if arg="53"/>
			<push arg="54"/>
			<load arg="29"/>
			<call arg="55"/>
			<goto arg="56"/>
			<load arg="29"/>
			<call arg="55"/>
			<store arg="19"/>
			<enditerate/>
			<load arg="19"/>
		</code>
		<linenumbertable>
			<lne id="57" begin="0" end="0"/>
			<lne id="58" begin="5" end="5"/>
			<lne id="59" begin="5" end="6"/>
			<lne id="60" begin="9" end="9"/>
			<lne id="61" begin="9" end="10"/>
			<lne id="62" begin="2" end="12"/>
			<lne id="63" begin="2" end="13"/>
			<lne id="64" begin="16" end="16"/>
			<lne id="65" begin="17" end="17"/>
			<lne id="66" begin="18" end="18"/>
			<lne id="67" begin="17" end="19"/>
			<lne id="68" begin="21" end="21"/>
			<lne id="69" begin="22" end="22"/>
			<lne id="70" begin="21" end="23"/>
			<lne id="71" begin="25" end="25"/>
			<lne id="72" begin="17" end="25"/>
			<lne id="73" begin="16" end="26"/>
			<lne id="74" begin="0" end="29"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="33" begin="8" end="11"/>
			<lve slot="2" name="75" begin="15" end="27"/>
			<lve slot="1" name="76" begin="1" end="29"/>
			<lve slot="0" name="17" begin="0" end="29"/>
		</localvariabletable>
	</operation>
	<operation name="77">
		<context type="45"/>
		<parameters>
		</parameters>
		<code>
			<pushi arg="47"/>
			<store arg="19"/>
			<push arg="28"/>
			<push arg="8"/>
			<new/>
			<load arg="47"/>
			<get arg="48"/>
			<iterate/>
			<store arg="29"/>
			<load arg="29"/>
			<get arg="78"/>
			<call arg="50"/>
			<enditerate/>
			<iterate/>
			<store arg="29"/>
			<load arg="19"/>
			<load arg="29"/>
			<call arg="55"/>
			<store arg="19"/>
			<enditerate/>
			<load arg="19"/>
		</code>
		<linenumbertable>
			<lne id="79" begin="0" end="0"/>
			<lne id="80" begin="5" end="5"/>
			<lne id="81" begin="5" end="6"/>
			<lne id="82" begin="9" end="9"/>
			<lne id="83" begin="9" end="10"/>
			<lne id="84" begin="2" end="12"/>
			<lne id="85" begin="15" end="15"/>
			<lne id="86" begin="16" end="16"/>
			<lne id="87" begin="15" end="17"/>
			<lne id="88" begin="0" end="20"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="2" name="89" begin="8" end="11"/>
			<lve slot="2" name="90" begin="14" end="18"/>
			<lve slot="1" name="76" begin="1" end="20"/>
			<lve slot="0" name="17" begin="0" end="20"/>
		</localvariabletable>
	</operation>
	<operation name="91">
		<context type="6"/>
		<parameters>
		</parameters>
		<code>
			<push arg="92"/>
			<push arg="92"/>
			<findme/>
			<push arg="93"/>
			<call arg="94"/>
			<iterate/>
			<store arg="19"/>
			<load arg="19"/>
			<call arg="95"/>
			<pushi arg="29"/>
			<call arg="96"/>
			<call arg="97"/>
			<if arg="98"/>
			<getasm/>
			<get arg="1"/>
			<push arg="99"/>
			<push arg="8"/>
			<new/>
			<dup/>
			<push arg="0"/>
			<pcall arg="100"/>
			<dup/>
			<push arg="101"/>
			<load arg="19"/>
			<pcall arg="102"/>
			<dup/>
			<push arg="103"/>
			<push arg="104"/>
			<push arg="104"/>
			<new/>
			<pcall arg="105"/>
			<pusht/>
			<pcall arg="106"/>
			<enditerate/>
		</code>
		<linenumbertable>
			<lne id="107" begin="7" end="7"/>
			<lne id="108" begin="7" end="8"/>
			<lne id="109" begin="9" end="9"/>
			<lne id="110" begin="7" end="10"/>
			<lne id="111" begin="25" end="30"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="1" name="101" begin="6" end="32"/>
			<lve slot="0" name="17" begin="0" end="33"/>
		</localvariabletable>
	</operation>
	<operation name="112">
		<context type="6"/>
		<parameters>
			<parameter name="19" type="113"/>
		</parameters>
		<code>
			<load arg="19"/>
			<push arg="101"/>
			<call arg="114"/>
			<store arg="29"/>
			<load arg="19"/>
			<push arg="103"/>
			<call arg="115"/>
			<store arg="116"/>
			<load arg="116"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<get arg="117"/>
			<call arg="30"/>
			<set arg="78"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<call arg="118"/>
			<call arg="30"/>
			<set arg="119"/>
			<dup/>
			<getasm/>
			<load arg="29"/>
			<call arg="95"/>
			<call arg="30"/>
			<set arg="78"/>
			<pop/>
		</code>
		<linenumbertable>
			<lne id="120" begin="11" end="11"/>
			<lne id="121" begin="11" end="12"/>
			<lne id="122" begin="9" end="14"/>
			<lne id="123" begin="17" end="17"/>
			<lne id="124" begin="17" end="18"/>
			<lne id="125" begin="15" end="20"/>
			<lne id="126" begin="23" end="23"/>
			<lne id="127" begin="23" end="24"/>
			<lne id="128" begin="21" end="26"/>
			<lne id="111" begin="8" end="27"/>
		</linenumbertable>
		<localvariabletable>
			<lve slot="3" name="103" begin="7" end="27"/>
			<lve slot="2" name="101" begin="3" end="27"/>
			<lve slot="0" name="17" begin="0" end="27"/>
			<lve slot="1" name="129" begin="0" end="27"/>
		</localvariabletable>
	</operation>
</asm>
