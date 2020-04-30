package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.openjfx.Main;
import org.openjfx.domain.Audio;
import org.openjfx.domain.Video;
import org.openjfx.service.SettingUtils;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class SettingPaneController implements Initializable {
//    文件输入栏
    @FXML
    private TextField fileText;
//  帧率选择
    @FXML
    private ChoiceBox frameChoiceBox;
//  保存格式选择
    @FXML
    private ChoiceBox formatChoiceBox;
//    分辨率选择框
    @FXML
    private ChoiceBox resolutionChoiceBox;

//    保存按钮
    @FXML
    private Button saveSettingButton;
    /**
     * 选择文件夹按钮
     */
    @FXML
    private Button showFileButton;


//    设置保存类
    private SettingUtils settingUtils;

//    选项集合
    private String[] frames;
    private String[] formats;
    private String[] resolutions;
    private Window stage;
    private Stage primaryStage;

    /**
    * 选取用户文件夹目录
    * @author      qiushao
    * @date        20-4-30 下午1:59
    */
    @FXML
    private void showFile() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        String path = file.getPath();//选择的文件夹路径
        fileText.setText(path);
    }


    /**
    * 保存用户设置信息
    * @author      qiushao
    * @date        20-4-30 下午2:00
    */
    @FXML
    private void saveSetting(){
        settingUtils=new SettingUtils();
        Video video=new Video();
        Audio audio=new Audio();
        video.setSaveFormat(formatChoiceBox.getValue().toString());
//        将object转成string再转成double类型
        video.setFrameRate(Double.valueOf(frameChoiceBox.getValue().toString()));
//        设置视频长宽
        video.setWidthAndHeiht(resolutionChoiceBox.getValue().toString());

//        System.out.println(video.getFrameRate());

        settingUtils.writeJsonFile(video,audio);




    }



    /**
    * 设置界面初始化
    * @author      qiushao
    * @date        20-4-27 下午4:30
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//      初始化选项
        frames =new String[]{"10", "15", "30","60"};
        formats=new String[]{"flv","mp4"};
        resolutions=new String[]{"1920*1080","1650*1050","1024*768"};
//      添加到选择框处
        frameChoiceBox.getItems().addAll(frames);
        formatChoiceBox.getItems().addAll(formats);
        resolutionChoiceBox.getItems().addAll(resolutions);
//        设置默认选项
        frameChoiceBox.getSelectionModel().select(0);
        formatChoiceBox.getSelectionModel().select(0);
        resolutionChoiceBox.getSelectionModel().select(0);
    }



}



