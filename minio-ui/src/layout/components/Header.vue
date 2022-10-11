<template>
  <div class="header">
    <div class="mi-h1">
        <div>小问号的文件夹</div>
    </div>
    <div class="mi-h2">
      <input type="text" placeholder="输入文件名" class="mi-input" maxlength="16" />
    </div>
    <div class="mi-h3">
      <div class="mi-button">
        <i class="el-icon-setting"></i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Header',
  components: {

  },


}
</script>

<style lang="scss" scoped>
   .header {
     display: flex;
   }
   .mi-h1 {
     flex: 1;
     display: flex;
     //justify-content: center;
     padding-left: 2%;
     align-items: center;
     color: #073052;
     font-size: 20px;
     font-weight: 600;
   }

   .mi-h2 {
     flex: 2;
     display: flex;
     justify-content: center;
     align-items: center;
   }

   .mi-h3 {
     flex: 1;
     display: flex;
     justify-content: right;
     align-items: center;
     padding-right: 2%;
   }

   .mi-input{
     outline-style: none ;
     border: 1px solid #EAEDEE;
     border-radius: 3px;
     padding: 13px 14px;
     width: 100%;
     font-size: 6px;
     font-weight: 700;
   }
   .mi-input:focus{
     border-color: #484848;
     outline: 0;
     //-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
     //box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
   }

   .mi-button {
     width: 42px;
     height: 39px;
     display: flex;
     padding: 0;
     justify-content: center;
     align-items: center;
     border: 1px solid rgb(91, 92, 92);
     border-radius: 3px;
   }

   .mi-button:hover {
     background-color: rgb(242, 242, 242);;
   }
</style>
