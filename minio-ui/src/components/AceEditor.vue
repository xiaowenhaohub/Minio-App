<template>
    <div class="ace-container">
        <d class="ace-header"></d>
        <!-- 官方文档中使用 id，这里禁止使用，在后期打包后容易出现问题，使用 ref 或者 DOM 就行 -->
        <div id="code" ref="ace" class="ace-editor"></div>
    </div>
</template>
  
<script>
import ace from 'ace-builds';
import 'ace-builds/webpack-resolver'; // 在 webpack 环境中使用必须要导入
import 'ace-builds/src-noconflict/theme-monokai'; // 默认设置的主题
import 'ace-builds/src-noconflict/theme-dracula';
import 'ace-builds/src-noconflict/theme-xcode';
import 'ace-builds/src-noconflict/mode-javascript'; // 默认设置的语言模式
import 'ace-builds/src-min-noconflict/mode-python'; // python
import 'ace-builds/src-min-noconflict/mode-batchfile'; // bash
import 'ace-builds/src-min-noconflict/mode-powershell'; // powershell
import 'ace-builds/src-min-noconflict/mode-yaml'; // yaml
import 'ace-builds/src-min-noconflict/mode-text'; // 文本
import 'ace-builds/src-noconflict/snippets/text';
import 'ace-builds/src-min-noconflict/ext-language_tools';

export default {
    name: 'AceEditor',
    props: {
        value: {
            type: String,
            default: ''
        },
        themePath: {
            type: String,
            default: 'ace/theme/chrome'
        },
        modePath: {
            type: String,
            default: 'text'
        },
        maxLines: {
            type: Number,
            default: 1000
        },
        minLines: {
            type: Number,
            default: 50
        },
        valueChange: {
            type: Function
        }
    },
    data() {
        return {
            aceEditor: null
        };
    },
    watch: {
        value: {
            handler(newValue) {
                if (newValue === '' || newValue) {
                    this.aceEditor.setValue(newValue);
                }
            }
        },
        modePath: {
            handler(newValue) {
                if (newValue) {
                    this.aceEditor.getSession().setMode('ace/mode/' + newValue);
                }
            }
        },
        aceEditor(newValue) {
            console.log(newValue, 'newValue')

        }
    },
    mounted() {
        console.log(document.getElementsByClassName('ace-container')[0].clientHeight)
        let h = document.getElementsByClassName('ace-container')[0].clientHeight / 20
        this.aceEditor = ace.edit(this.$refs.ace, {
            value: this.value,
            maxLines: this.maxLines, // 最大行数，超过会自动出现滚动条
            minLines: h, // 最小行数，还未到最大行数时，编辑器会自动伸缩大小
            // minLines: this.minLines, // 最小行数，还未到最大行数时，编辑器会自动伸缩大小
            fontSize: 14, // 编辑器内字体大小
            theme: 'ace/theme/' + this.themePath, // 默认设置的主题
            mode: 'ace/mode/' + this.modePath, // 默认设置的语言模式
            tabSize: 4 // 制表符设置为 4 个空格大小
        });
        // 激活自动提示
        this.aceEditor.setOptions({
            enableSnippets: true,
            enableLiveAutocompletion: true,
            enableBasicAutocompletion: true
        });
        document.getElementById("code").style.lineHeight = "20px"
        this.aceEditor.setHighlightActiveLine(true);
        this.aceEditor.getSession().setUseWrapMode(true);
        this.aceEditor.setShowPrintMargin(false);
        this.aceEditor.getSession().setUseWorker(false);
        // this.aceEditor.getSession().on('change', this.change);
        this.aceEditor.setHighlightActiveLine(false);
        this.aceEditor.getSession().on('change', this.change);

    },
    methods: {
        change() {
            this.valueChange(this.aceEditor.getSession().getValue())
            // console.log(this.aceEditor.getSession().getValue(), 'change')
            // this.$emit('input', this.aceEditor.getSession().getValue());
        }
    }
};

</script>
<style lang='scss' scoped>
.ace-container {
    // position: relative;
    width: 100%;
    height: 100%;
    box-shadow: 0 4px 30px rgb(0 0 0 / 50%);
    border-radius: 6px;
    overflow-y: scroll;
    // overflow: hidden;

    .ace-editor {
        width: 100%;
        height: 100%;
    }

    .ace-header {
        width: 100%;
        height: 20px;
    }

    .bookmarklet {
        position: absolute;
        right: 0;
        bottom: 0;
        width: 20px;
        height: 20px;
        z-index: 2;
        cursor: pointer;
        border-width: 9px;
        border-style: solid;
        border-color: lightblue gray gray rgb(206, 173, 230);
        border-image: initial;
    }
}
</style>
  
  