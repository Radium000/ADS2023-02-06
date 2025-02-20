package by.it.group251004.Ivannikova.lesson09;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListC<E> implements List<E> {
    private E[] list = (E[]) new Object[0];
    private int size = 0;

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder str = new StringBuilder("[");

        str.append(list[0]);
        for (int i = 1; i < size; i++) {
            str.append(", ").append(list[i]);
        }

        return str.append("]").toString();
    }

    @Override
    public boolean add(E e) {
        if (size == list.length) {
            resize();
        }

        add(size, e);
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index: " + index + " is out of bound size: " + size);

        E deleted = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;

        return deleted;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("index: " + index + "is out of bound, size: " + size);

        if (size == list.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }

        list[index] = element;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);
        if (idx < 0) {
            return false;
        }

        remove(idx);
        return true;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bound");
        }

        E prevElement = list[index];
        list[index] = element;

        return prevElement;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            list[i] = null;
        }
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Type \"null\" is invalid");
        }

        for (int i = 0; i < size; i++) {
            if (o.equals(list[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bound");
        }
        return list[index];
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Type \"null\" is invalid");
        }

        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(list[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bound");
        }
        int cSize = c.size();

        if (c.isEmpty()) {
            return false;
        }

        if (size + cSize > list.length) {
            resize(size + cSize);
        }

        for (int i = size - 1; i >= index; i--) {
            list[i + cSize] = list[i];
        }

        int i = index;
        for (E obj : c) {
            list[i++] = obj;
        }

        size = size + cSize;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;

        for (int i = 0; i < size; i++)
            if (c.contains(list[i])) {
                remove(i);
                res = true;
                i--;
            }

        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        int i = 0;
        while (i < size) {
            if (!c.contains(list[i])) {
                remove(list[i]);
                res = true;
            } else {
                i++;
            }
        }

        return res;
    }

    private void resize() {
        resize(list.length);
    }

    private void resize(int newSize) {
        E[] newList = (E[]) new Object[2 * newSize + 1];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    //////               Опциональные к реализации методы             ///////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////        Эти методы имплементировать необязательно    ////////////
    ////////        но они будут нужны для корректной отладки    ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    @Override
    public Iterator<E> iterator() {
        return null;
    }

}
