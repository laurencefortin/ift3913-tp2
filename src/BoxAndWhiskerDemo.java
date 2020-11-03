

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.LogContext;

public class BoxAndWhiskerDemo extends ApplicationFrame {

    /** Access to logging facilities. */
    private static final LogContext LOGGER = Log.createContext(BoxAndWhiskerDemo.class);

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BoxAndWhiskerDemo(final String title, ArrayList<Classe> liste) {

        super(title);
        
        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset(liste);

        final CategoryAxis xAxis = new CategoryAxis("Type");
        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(true);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.WHITE);
        renderer.setSeriesPaint(1, Color.LIGHT_GRAY);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setSeriesOutlinePaint(1, Color.BLACK);
        renderer.setUseOutlinePaintForWhiskers(true);   
        Font legendFont = new Font("SansSerif", Font.PLAIN, 16);
        renderer.setLegendTextFont(0, legendFont);
        renderer.setLegendTextFont(1, legendFont);
        renderer.setMedianVisible(true);
        renderer.setMeanVisible(true);
        renderer.setMaximumBarWidth(0.5);

        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        final JFreeChart chart = new JFreeChart(
            "Box-and-Whisker Demo",
            new Font("SansSerif", Font.BOLD, 14),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(650, 370));
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private BoxAndWhiskerCategoryDataset createSampleDataset(ArrayList<Classe> liste) {
        
        final int seriesCount = 4;
        final int categoryCount = 1;
        final int entityCount = 1;
        
        
  /*     final DefaultBoxAndWhiskerCategoryDataset dataset 
            = new DefaultBoxAndWhiskerCategoryDataset();
        for (int i = 0; i < seriesCount; i++) {
            for (int j = 0; j < categoryCount; j++) {
                final List list = new ArrayList();
                // add some values...
                for (int k = 0; k < entityCount; k++) {
                    final double value1 = 5.0;
                    list.add(new Double(value1));
                    final double value2 = 11.25 + Math.random(); // concentrate values in the middle
                    list.add(new Double(value2));
                }
                LOGGER.debug("Adding series " + i);
                LOGGER.debug(list.toString());
                dataset.add(list, "C" + i, " Categorie " + j);
            }
            
        }*/
        
        
        final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
        final List list = new ArrayList();
        /*for (int i = 0; i < liste.size(); i++) {
        	{
        		list.add(liste.get(i).NOM);
            };
            
        }
        dataset.add(list, "Classes", "NOM");
        list.clear();*/
        for (int i = 0; i < liste.size(); i++) {
        	{
        		list.add(liste.get(i).CAC);
            };
            
        }
        dataset.add(list, "Classes", "CAC");
        list.clear();
        for (int i = 0; i < liste.size(); i++) {
        	{
        		list.add(liste.get(i).DIT);
            };
            
        }
        dataset.add(list, "Classes", "DIT");
        list.clear();
        for (int i = 0; i < liste.size(); i++) {
        	{
        		list.add(liste.get(i).NEC);
            };
            
        }
        dataset.add(list, "Classes", "NEC");
        
        return dataset;
    }
    
      

    /**
     * For testing from the command line.
     *
     * @param args  ignored.
     * @throws IOException 
     */
    public static void main(final String[] args) throws IOException {

    	 ArrayList<Classe> liste = new ArrayList<Classe>();
        //Log.getInstance().addTarget(new PrintStreamLogTarget(System.out));
        
        try (           		
                Reader reader = Files.newBufferedReader(Paths.get("./tp2_donnees.csv"));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
            ) {
                for (CSVRecord csvRecord : csvParser) {
                    // Accessing values by Header names
                	String id = csvRecord.get("Classes");
                    String NOM = csvRecord.get("NOM");
                    String DIT = csvRecord.get("DIT");
                    String CAC = csvRecord.get("CAC");
                    String NEC = csvRecord.get("NEC");

                    System.out.println("---------------");
                    System.out.println("Classe : " + id);
                    System.out.println("NOM : " + NOM);
                    System.out.println("DIT : " + DIT);
                    System.out.println("CAC : " + CAC);
                    System.out.println("NEC : " + NEC);
                    System.out.println("---------------\n\n");
                    
                    Classe temp = new Classe(id, Integer.parseInt(NOM), Integer.parseInt(DIT), Integer.parseInt(CAC), Integer.parseInt(NEC));
                    liste.add(temp);
                }
            }

        final BoxAndWhiskerDemo demo = new BoxAndWhiskerDemo("Box-and-Whisker Chart Demo", liste);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
