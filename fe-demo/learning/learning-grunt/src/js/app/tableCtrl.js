define(['angular','mainApp'],function(angualr,app){
  app.controller('tableCtrl',function($scope,$http){
    $scope.records=[
      {
        name:'张三',
        sex:'男',
        age:'27'
      },
      {
        name:'张三',
        sex:'男',
        age:'35'
      },
      {
        name:'张三',
        sex:'男',
        age:'53'
      },
      {
        name:'张三',
        sex:'男',
        age:'17'
      },
      {
        name:'张三',
        sex:'男',
        age:'47'
      }
    ]
  })
})