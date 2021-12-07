package business.services;

public class SVG {
    StringBuilder svg = new StringBuilder();
    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    private final String headerTemplate = "<svg  height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox=\"%s\" " +
            "x=\" %d\" "+
            "y=\" %d\" "+
            "preserveAspectRatio=\"xMinYMin\">";
    private final String rectTemplate = "<rect x=\"%f\"  y=\"%f\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: white\"/>";
    private final String lineTemplate = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke-dasharray: 4; stroke: black\"></line>";
    private final String normalLine = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke: black\"></line>";
    private final String lineTemplateForShed = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke-dasharray: 8; stroke: black; stroke-width: 3\"></line>";
    private final String arrows = "<defs>\n" +
            "        <marker\n" +
            "                id=\"beginArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"0\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "        <marker\n" +
            "                id=\"endArrow\"\n" +
            "                markerWidth=\"12\"\n" +
            "                markerHeight=\"12\"\n" +
            "                refX=\"12\"\n" +
            "                refY=\"6\"\n" +
            "                orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "    </defs>";
    private final String arrowLine = "<line x1=\"%f\"  y1=\"%f\" x2=\"%f\" y2=\"%f\"\n" +
            "          style=\"stroke: black;\n" +
            "\tmarker-start: url(#beginArrow);\n" +
            "    marker-end: url(#endArrow);\"/>";
    private final String rotatedText ="<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(-90)\">%s</text>";
    private final String text ="<text style=\"text-anchor: middle\" transform=\"translate(%f,%f)\">%s</text>";
    private final String rotatedRect ="<g transform=\"rotate(0.75)\">\n" +
            "      <rect x=\"%f\" y=\"%f\" height=\"%f\" width=\"%f \"\n" +
            "            style=\"stroke:#000000; fill: white\"/>\n" +
            "      </g>";


    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate,height,width,viewBox,x,y));
        svg.append(arrows);
    }


    public void addRect(double x, double y, double height, double width){
        svg.append(String.format(rectTemplate,x,y,height,width));

    }

    public void addDottedLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(lineTemplate,x1,y1,x2,y2));

    }
    public void addLineForShed(double x1, double y1, double x2, double y2){
        svg.append(String.format(lineTemplateForShed,x1,y1,x2,y2));

    }

    public void addSvg(SVG innerSVG){
        svg.append(innerSVG.toString());
    }

    public void addArrowLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(arrowLine,x1,y1,x2,y2));
    }

    public void addRoatedText(double x, double y, String length){
        svg.append(String.format(rotatedText,x,y,length));
    }
    public void addText(double x, double y, String length){
        svg.append(String.format(text,x,y,length));
    }

    public void addLine(double x1, double y1, double x2, double y2){
        svg.append(String.format(normalLine,x1,y1,x2,y2));
    }

    public void addRotatedRect(double x, double y, double height, double width){
        svg.append(String.format(rotatedRect,x,y,height,width));
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}

