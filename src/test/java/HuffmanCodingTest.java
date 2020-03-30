import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HuffmanCodingTest {
    private List<Sign> signs;
    private HuffmanCoding huffmanCoding;

    @Before
    public void setUp() {

        Sign a = new Sign('a', 0.1);
        Sign b = new Sign('b', 0.1);
        Sign c = new Sign('c', 0.15);
        Sign d = new Sign('d', 0.2);
        Sign e = new Sign('e', 0.45);

        signs = new ArrayList<>();
        signs.add(a);
        signs.add(b);
        signs.add(c);
        signs.add(d);
        signs.add(e);

        huffmanCoding = new HuffmanCoding(signs);
    }


    @Test
    public void shouldCorrectEncode() {
        String encoded = huffmanCoding.encode("babecda");
        assertEquals("1111111011110110101110", encoded);
    }

    @Test
    public void shouldCorrectDecode() {
        String decoded = huffmanCoding.decode("1111111011110110101110");
        assertEquals("babecda", decoded);
    }
}