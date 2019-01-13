<?php
$yii=dirname(__FILE__).'/../yii/framework/yii.php';
$config=dirname(__FILE__).'/protected/config/config.php';

defined('YII_PRODUCTION') || define('YII_PRODUCTION', strstr(__FILE__,'/httpdocs/auditplace'));

// remove the following lines when in production mode
if (!strstr(__FILE__,'httpdocs')) :
   defined('YII_DEBUG') || define('YII_DEBUG',true);
endif;
// specify how many levels of call stack should be shown in each log message
defined('YII_TRACE_LEVEL') or define('YII_TRACE_LEVEL',3);

require_once($yii);
require_once(dirname(__FILE__).'/_helper.php');
Yii::createWebApplication($config)->run();
