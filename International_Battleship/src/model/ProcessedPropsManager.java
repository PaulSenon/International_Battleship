package model;

import tools.ProcessedProps;

import java.util.ArrayList;
import java.util.List;

public class ProcessedPropsManager {
    private static List<ProcessedProps> processedPropsToUpdate;
    private static ProcessedPropsManager instance;

    public static ProcessedPropsManager newInstance(){
        instance = new ProcessedPropsManager();
        return instance;
    }

    public static ProcessedPropsManager getInstance(){
        if(instance == null){
            return newInstance();
        }
        return instance;
    }

    private ProcessedPropsManager(){
        processedPropsToUpdate = new ArrayList<>();
    }

    public static List<ProcessedProps> flushQueue() {
        List<ProcessedProps> tmp = new ArrayList<>(processedPropsToUpdate);
        processedPropsToUpdate.clear();
        return tmp;
    }

    public static void addToQueue(ProcessedProps processedProps){
        processedPropsToUpdate.add(processedProps);
    }

}
